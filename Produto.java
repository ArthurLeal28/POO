package loja;

public class Produto {
	private int codigo;
	private String nome;
	private String marca;
	private double preco;
	private int quantNoEstoque;
        private int estado;

	private static int geraCodigo;

	private Produto(String nome, String marca, double preco, int quantNoEstoque) {
		codigo = ++geraCodigo;
		this.nome = nome;
		this.marca = marca;
		this.preco = preco;
		this.quantNoEstoque = quantNoEstoque;
                this.estado = 1;
	}

	public static Produto getInstance(String nome, String marca, double preco, int quantNoEstoque) {
		if (nome != null && marca != null && preco >= 0 && quantNoEstoque >= 0) {
			return new Produto(nome, marca, preco, quantNoEstoque);
		} else
			return null;
	}
        
        public Produto(Produto outro){
            if(outro != null){
                this.codigo = outro.codigo;
                this.nome = outro.nome;
                this.marca = outro.marca;
                this.preco = outro.preco;
                this.quantNoEstoque = outro.quantNoEstoque;
                this.estado = outro.estado;
            }
        }
         public boolean RemoverUnidade(int quantidade){
            if(this.quantNoEstoque >= quantidade)
              {
                  quantNoEstoque = quantNoEstoque - quantidade;
                  return true;
              }
            
            return false;
        }
         
    

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null)
			this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if (marca != null)
			this.marca = marca;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		if (preco >= 0)
			this.preco = preco;
	}

	public int getQuantidade() {
		return quantNoEstoque;
	}

	public void setQuantNoEstoque(int quantNoEstoque) {
		if (quantNoEstoque >= 0)
			this.quantNoEstoque = quantNoEstoque;
	}

        public void setEstado(int estado) {
            if( estado >= 0 || estado <=1)
                this.estado = estado;
        }

    public int getEstado() {
        return this.estado;
    }

}