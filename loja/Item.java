package loja;

public class Item {
	private Produto produto;
	private double preco;
	private int quantidade;

    private Item(Produto produto,double preco,int quantidade){
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    public Item(Item outro){
       if(outro!=null){
        this.preco =outro.preco;
        this.produto =outro.produto;
        this.quantidade = outro.quantidade;
       }
    }
    public static Item getInstance(Produto produto, double preco,int quantidade) {
		if (produto!=null && preco >= 0 && quantidade >= 0) {
			return new Item(produto,preco,quantidade);
		} else
			return null;
    }
    public Produto getProduto() {
        Produto aux = new Produto(this.produto);
        return aux;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
        
        
}
