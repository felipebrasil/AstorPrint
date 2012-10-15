import java.awt.Graphics; 
import java.awt.PrintJob; 
import java.awt.Toolkit; 
import java.awt.Frame;

public class Impressora {
	public void imprimir() {
		// cria um frame temporário, onde será ￼ desenhado ￼ o texto a ser impresso 
		Frame f = new Frame("Frame temporário");
		f.pack();
		// pega o Toolkit do Frame
		Toolkit tk = f.getToolkit();
		// Pega os serviços de impressão existentes no computador, 
		// para que seja escolhida uma impressora.
		// Também pode ser uma impressora de rede
		PrintJob pj = tk.getPrintJob(f, "print" , null);
		// Aqui se inicia a impressão
		if (pj != null) {
			Graphics g = pj.getGraphics();
			g.drawString( "Teste de impressão" , 50, 30); 
			g.drawString( "linha 1" , 50, 70);
			g.drawString( "linha 2" , 50, 95); 
			g.drawString( "página 1" , 500, 810);
			// libera os recursos gráficos
			g.dispose();
			pj.end(); 
			}
		// desfaz o frame temporário
		f.dispose(); 
		}
		// Método main para teste
		public static void main(String[] args) { 
			Impressora imp = new Impressora();
			imp.imprimir();
		}
	}