package block1;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Exception;
public class Merge{
	public static int[][] merge(int net1[][], int net2[][]){
		int pNet1 = net1.length;
		int pNet2 = net2.length;
		int tNet1 = net1[0].length;
		int tNet2 = net2[0].length;
		int newMatrix[][] = new int[pNet1+pNet2][tNet1];
		for (int p=0;p<pNet1;p++){
			for (int t=0;t<tNet1;t++) {
				newMatrix[p][t] = net1[p][t];
			}
		}
		for (int p=0;p<pNet2;p++){
			for (int t=0;t<tNet2;t++) {
				newMatrix[p+pNet1][t] = net2[p][t];
			}
		}
		return newMatrix; 
	}
	public static void printMatrix(int net[][]){
		int pNet = net.length;
		int tNet = net[0].length;
		for(int p=0;p<pNet;p++){
			for(int t=0;t<tNet;t++){
				System.out.print(net[p][t]+"\t");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}
        /**
         * This load 2 matrix files and are wrapped into the object twomatrix
         * @param file1
         * @param file2
         * @return 
         */
        public static TwoMatrix readMatrix(String file1, String file2){
            TwoMatrix m;
		int net1[][];
		int net2[][];
		File oFile;
		File nFile;
		Scanner input;
		Scanner colReader;;
		try{
			oFile = new File(file1);
			nFile = new File(file2);
			input = new Scanner (oFile);
			int rows = 0;
			int columns = 0;
			while(input.hasNextLine())
			{
			    ++rows;
			    colReader = new Scanner(input.nextLine());
			    columns = 0;
			    while(colReader.hasNextInt())
			    {
			    	colReader.nextInt();
			        ++columns;
			    }
			}
			net1 = new int[rows][columns];

			input.close();
			// read in the data
			input = new Scanner(oFile);
			for(int i = 0; i < rows; ++i)
			{
			    for(int j = 0; j < columns; ++j)
			    {
			        if(input.hasNextInt())
			        {
			            net1[i][j] = input.nextInt();
			        }
			    }
			}
			input.close();
			input = new Scanner (nFile);
			rows = 0;
			while(input.hasNextLine())
			{
			    ++rows;
			    input.nextLine();
			}
			net2 = new int[rows][columns];
			input.close();
			// read in the data
			input = new Scanner(nFile);
			for(int i = 0; i < rows; ++i)
			{
			    for(int j = 0; j < columns; ++j)
			    {
			        if(input.hasNextInt())
			        {
			            net2[i][j] = input.nextInt();
			        }
			    }
			}
                        m = new TwoMatrix(net1,net2);
		}
		catch(Exception fnfe){
			fnfe.printStackTrace();
                        m=null;
		}
               
                return m;
        }
        /**
         * This method is for export the petri network to xml
         * @param matrix
         * @param fileName 
         */
        public static void exportNet(int [][] matrix, String fileName){
			//Construction of the petri net
			List<Place> placeList = new ArrayList<Place>();
			List<Transition> transitionList = new ArrayList<Transition>();
			List<Arc> arcList = new ArrayList<Arc>();
			PNML pnml = new PNML();

			//Create the list of places
			int mP = matrix.length;
			int initX = 50;
			int initY = 50;
			for(int p=1,c=0; p<=mP; p++,c+=50){
				Place newPlace = new Place();
				newPlace.setId("P"+p);
				newPlace.setGraphics(initX+c,initY);
				newPlace.setName(0,0);
				newPlace.setInitialMarking("Default",0,0,0);
				newPlace.setCapacity(0);
				placeList.add(p-1,newPlace);
			}


			//Create the list of transitions
			int mT = matrix[0].length;
			initY = 200;
			for(int t=1,c=0; t<=mT; t++,c+=100){
				Transition newTransition = new Transition();
				newTransition.setId("T"+t);
				newTransition.setGraphics(initX+c,initY);
				newTransition.setName(0,0);
				newTransition.setOrientation(0);
				newTransition.setRate(1.0f);
				newTransition.setTimed(false);
				newTransition.setInfiniteServer(false);
				newTransition.setPriority(1);
				transitionList.add(t-1,newTransition);	
			}	

			//Create the list of arcs
			for(int p=1;p<=mP;p++){
				for(int t=1;t<=mT;t++){
					if(matrix[p-1][t-1]==1){//A new arc from t to p
						Arc newArc = new Arc();
						newArc.setId("T"+t+" to T"+p);
						newArc.setSource("T"+t);
						newArc.setTarget("P"+p);
						newArc.setInscription("Default,1");
						newArc.setTagged(false);
						ArcPath arcPath1 = new ArcPath("000",0,0,false);
						ArcPath arcPath2 = new ArcPath("001",0,0,false);
						List<ArcPath> arcPathList = new ArrayList<ArcPath>();
						arcPathList.add(arcPath1);
						arcPathList.add(arcPath2);
						newArc.setArcPath(arcPathList);
						newArc.setType("normal");
						arcList.add(newArc);
					}
					else if(matrix[p-1][t-1]==-1){//A new arc from p to t
						Arc newArc = new Arc();
						newArc.setId("P"+p+" to T"+t);
						newArc.setSource("P"+p);
						newArc.setTarget("T"+t);
						newArc.setInscription("Default,1");
						newArc.setTagged(false);
						ArcPath arcPath1 = new ArcPath("000",0,0,false);
						ArcPath arcPath2 = new ArcPath("001",0,0,false);
						List<ArcPath> arcPathList = new ArrayList<ArcPath>();
						arcPathList.add(arcPath1);
						arcPathList.add(arcPath2);
						newArc.setArcPath(arcPathList);
						newArc.setType("normal");
						arcList.add(newArc);
					}
				}
			}

			//Initialize the petri net and create XML
			pnml.setNet(placeList,transitionList,arcList,"Net-One","P/T net","Default","true",0,0,0);

			try {
				File file = new File(fileName);
				JAXBContext jaxbContext = JAXBContext.newInstance(PNML.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(pnml, file);
<<<<<<< HEAD
				//jaxbMarshaller.marshal(pnml, System.out);
=======
				jaxbMarshaller.marshal(pnml, System.out);
>>>>>>> Equipo2
			} 
		    catch (JAXBException e){
				e.printStackTrace();
		    }

        }
        /**
         * This method is for reduce the merged matrix
         * @param matrix1
         * @param matrix2
         * @return 
         */
        public static int[][] reduceMatrix(int[][] matrix1,int[][] matrix2){
        MRow rowsO[]=new MRow[matrix1.length];
        MRow rowsNO[]=new MRow[matrix2.length];
        /*pasa el array 2d a una clase row*/
        for(int i=0;i<rowsO.length;i++){
            rowsO[i]=new MRow(matrix1[i],false);
        }
        for(int i=0;i<rowsNO.length;i++){
            rowsNO[i]=new MRow(matrix2[i],true);
        }
        //c=numero de filas
        int c=rowsNO.length;
        /*
        Aqui se marcan cuales filas están repetidas
         */
        for (MRow rowsO1 : rowsO) {
            for (MRow rowsNO1 : rowsNO) {
                if (rowsO1.isEquals(rowsNO1) && rowsNO1.mark == false) {
                    rowsNO1.mark = true;
                    c--;
                }
            }
        }
        /*declaramos una nueva matriz*/
        int newMatrix[][]=new int[c+rowsO.length][matrix1[0].length];
        int j=0;
        for (MRow row : rowsO) {
            if (row.mark == false) {
                newMatrix[j] = row.getRow();
                j++;
            }       
        }
        /*llenamos la nueva matriz, unicamente con los no marcados*/
        for (MRow row : rowsNO) {
            if (row.mark == false) {
                newMatrix[j] = row.getRow();
                j++;
            }       
        }
        return newMatrix;
        
    }
<<<<<<< HEAD
        
=======
>>>>>>> Equipo2
        /*       
	public static void main(String[] args) {
		TwoMatrix tm=readMatrix("observable.txt","non-observable.txt");
                int[][] a=Merge.merge(tm.MObservable, tm.MNOObservable);
                printMatrix(a);
                int[][] b=Merge.reduceMatrix(tm.MObservable, tm.MNOObservable);
                printMatrix(b);
                exportNet(b,"salida.xml");
	}*/
}