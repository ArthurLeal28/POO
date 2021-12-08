package loja;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {
	private int codigo;
	private Date data;
	private Item[] itensVendidos;
        private double valorTotal;
    
        private static int gerarcodigovenda;
    
    public Venda(Venda outro){
            if(outro != null){
                this.codigo = outro.codigo;
                this.data = outro.data;
                this.itensVendidos = outro.itensVendidos;
                this.valorTotal = outro.valorTotal;
            }
        }
    private Venda(Date data )
    {
        itensVendidos = new Item[20];
        this.codigo = ++gerarcodigovenda;
        this.data = data;
        this.valorTotal = 0.0;
    }
    
    public static Venda getInstance(Date data)
    {
        if(data!=null)
        {
            return new Venda(data);
        }
        return null;
    }
    
    public boolean VendaConfirmada(Carrinho carrinho)
    {
        Sistema sis = Sistema.getInstance();
        int codigo;
        int quantidade;
        if(carrinho.getItens() != null)
        {
            itensVendidos = carrinho.getItens();
            for(int i=0;i<itensVendidos.length;i++){
                if(itensVendidos[i]!=null){
                    codigo = itensVendidos[i].getProduto().getCodigo();
                    quantidade =itensVendidos[i].getQuantidade();
                    sis.removerEstoque(codigo,quantidade);
                }
            }
            return true;
        }
        return false;
    }
    
    public Item[] getItensVendidos() {
        return itensVendidos;
    }

    public void setItensVendidos(Item[] itensVendidos) {
        this.itensVendidos = itensVendidos;
    }
   
    public void valorVenda(){
        double valor = 0.0;
        for(int i=0;i<itensVendidos.length;i++){
            if(itensVendidos[i]!=null){
                valor = valor+(itensVendidos[i].getPreco()*itensVendidos[i].getQuantidade());
            }
        }
        this.valorTotal = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataformatada = dateFormat.format(data);
        return dataformatada;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public static int getGerarcodigovenda() {
        return gerarcodigovenda;
    }
        
}
