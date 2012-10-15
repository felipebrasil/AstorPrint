import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.nio.charset.*;

public class PrintTeste {

	public static void main(String args[]) {
		PrintTeste ps = new PrintTeste();
	}
	public PrintTeste() {

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		
		String s =  "R0,0\n" +   // Set Reference Point                                                             
                "N\n" +         // Clear Image Buffer                                                             
                "ZB\n" + // Print direction (from Bottom of buffer)
                "GW160,50,100,100,\r\n"+
                "Q122,16\n" +  // Set label Length and gap
                "A160,2,0,3,1,1,N,\"DATA:  - CARUGATE\"\n" +
                "B160,30,0,1A,2,7,50,N,\"6120\"\n" +                            
                "A160,92,0,1,1,1,N,\"AIA AGRICOLA IT.ALIMENT.S - 594679/VR                       \"\n" +
                "P1\n";   // Print content of buffer, 1 label
		byte[] by = s.getBytes();
		
		//DocFlavor.READER.
//		PrintRequestAttributeSet aset = 
//			new HashPrintRequestAttributeSet();
		//aset.add( new MediaSize(50, 100, Size2DSyntax.MM));
/*
		aset.add(new JobName("Impressao", null));
		aset.add(OrientationRequested.PORTRAIT);
		aset.add(MediaSizeName.ISO_A4);
		aset.add(new Copies(1));
	*/
		//aset.add(Sides.TWO_SIDED_LONG_EDGE);
		//aset.add(Fi nishings.STAPLE);
		
		/* locate a print service that can handle it */
		PrintService pservices =
			//PrintServiceLookup.lookupPrintServices(flavor, aset);
			PrintServiceLookup.lookupDefaultPrintService();
		//System.out.println("Printers "+pservices.length);
		if (pservices != null) {
			System.out.println("selected printer " + pservices.getName());
			
			/* create a print job for the chosen service */
			DocPrintJob pj = pservices.createPrintJob();
			try {
				/* 
				* Create a Doc object to hold the print data.
				* Since the data is postscript located in a disk file,
				* an input stream needs to be obtained
				* BasicDoc is a useful implementation that will if requested
				* close the stream when printing is completed.
				*/
				//String teste = "^XA\n\r^MNM\n\r^FO050,50\n\r^B8N,100,Y,N\n\r^FD1234567\n\r^FS\n\r^PQ3\n\r^XZ";
				//InputStream stream = new ByteArrayInputStream(teste.getBytes()); 
				//FileInputStream fis = new FileInputStream("/Users/felipebrasil/Downloads/Testepdfprint.pdf");
				Doc doc = new SimpleDoc(by, flavor, null);

				/*
				String czas = new SimpleDateFormat("d MMMMM yyyy'r.' HH:mm s's.'").format(new Date());
			    String command =  
			            "N\n"+
			            "A50,50,0,2,2,2,N,\"felipe\"\n"+
			            "B50,100,0,1,2,2,170,B,\"felipe\"\n"+
			            "A50,310,0,3,1,1,N,\"felipe\"\n"+
			            "P1\n"
			            ;

			    byte[] data;
			    data = command.getBytes();
			    Doc doc = new SimpleDoc(data, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
				*/
				
				/* print the doc as specified */
				pj.print(doc, null);

				/*
				* Do not explicitly call System.exit() when print returns.
				* Printing can be asynchronous so may be executing in a
				* separate thread.
				* If you want to explicitly exit the VM, use a print job 
				* listener to be notified when it is safe to do so.
				*/

			//} catch (IOException ie) { 
			//	System.err.println(ie);
			} catch (PrintException e) { 
				System.err.println(e);
			}
		}
	}
}

