package loja;

import java.util.Date;

public class Sistema {
	private Produto[] produtos;
	private int numProdutos;
	private Venda[] vendas;
	private int numVendas;
        
        private static Sistema instance;
	

	private Sistema() {
		produtos = new Produto[30];
                vendas = new Venda[30];
	}
         
        public static Sistema getInstance(){
            if(instance == null){
               instance = new Sistema(); 
            }
                return instance;
        }
        
	public void init() {
                Carrinho carrinho = Carrinho.getInstance();
                Date data = new Date();
		inserir(Produto.getInstance("Farinha", "Baiana", 2.50, 50));
		inserir(Produto.getInstance("Feijao", "Supang", 3.50, 100));
		inserir(Produto.getInstance("Beterraba", "oba", 2.00, 100));
                inserir(Produto.getInstance("Arroz Gran aaaa", "Prato Fino", 22.50, 120));
		carrinho.additem(1,10,1);
                fazerVenda(data,carrinho);
	}

	public boolean inserir(Produto produto) {
		Produto p = buscar(produto.getNome());
		if (p == null && numProdutos < produtos.length) {
                    for(int i=0;i<produtos.length;i++){
                        if(produtos[i]==null){
                            produtos[i] = produto;
                            numProdutos++;
                            return true;
                        }
                    }
		}
		return false;
	}

	public boolean excluir(int codigo) {
            Produto p = buscar(codigo);
                if (p!=null){
                    Produto x = buscarVenda(p.getCodigo());
                    if(x != null) {
                        Produto produto = buscarInterno(p.getCodigo());
                        produto.setEstado(0);
                        return true;
                    }
                }
 
               
			for (int indice = 0; indice < this.produtos.length; indice++) {

                            if (produtos[indice] != null && p != null) {     
                                if(produtos[indice].getCodigo() == codigo){

                                        produtos[indice] = null;
                                        numProdutos--;
                                        return true;
                                       }
                            }
                        }
                
                
		return false;
	}

	public boolean alterarProduto(int codigo,int opcao,String alteracao) {
		Produto p = buscar(codigo);
                for (int indice = 0; indice < this.produtos.length; indice++) {
                     if (produtos[indice] != null && produtos[indice].getCodigo() == p.getCodigo()) {
                            switch (opcao) {
                            case 1:
                                    produtos[indice].setNome(alteracao);
                                    break;

                            case 2:
                                    produtos[indice].setMarca(alteracao);
                                    break;

                            case 3:
                                     double preco = Double.parseDouble(alteracao);
                                     produtos[indice].setPreco(preco);
                                    break;

                            case 4:
                                    int quantidade = Integer.parseInt(alteracao);
                                    produtos[indice].setQuantNoEstoque(quantidade);
                                    break;

                            default:
                                    System.out.println("Nao foi possivel realizar a solicitacao.");
                                    return false;
                            }
                         }
                 }
                
                return true;
	}
        
        public Venda[] todasVendas(){
            Venda todas[] = new Venda[vendas.length];
            for(int i=0;i<vendas.length;i++){
                if(vendas[i]!=null)
                 todas[i]=new Venda(vendas[i]);
            }
            return todas;
        }
        public Produto[] buscarOrdemCadastro(){
            Produto[] cadastro = new Produto[produtos.length];
            Produto aux = null;
            int x =0;
            for (int indice = 0; indice < this.produtos.length; indice++) {
                if (produtos[indice] != null && produtos[indice].getEstado() == 1){
                    cadastro[x] = new Produto(produtos[indice]);
                    x++;
                }
            }
            for(int indice = 0; indice < cadastro.length; indice++){
               for(int c = 0; c < cadastro.length-1; c++){
                 if(cadastro[c] != null && cadastro[c+1] != null){
                  if(cadastro[c].getCodigo()>cadastro[c+1].getCodigo()){
                      aux = cadastro[c];
                      cadastro[c] = cadastro[c+1];
                      cadastro[c+1]=aux;
                      
                  }
                }
               }
            }
            return cadastro;
        }
        public Produto[] Ordemalf(){
                int x;
                x=numProdutos;

                Produto aux ;
                Produto []  vetord = new Produto[x];
                char n1;
                char n2;
                char n3;
                char n4;

                for(int l=0;l<x;l++)
                {
                    vetord[l]=new Produto(produtos[l]);
                }
               
                 for(int i=0;i<x;i++)
                 {
                     for(int c=0;c<x;c++)
                     {
                         n1 = vetord[c].getNome().charAt(0);
                         n2 = vetord[i].getNome().charAt(0);
                         n3 = vetord[c].getNome().charAt(1);
                         n4 = vetord[i].getNome().charAt(1);
                         if(n1>n2)
                         {
                             aux = vetord[c];
                             vetord[c]=vetord[i];
                             vetord[i]=aux;
                         }else if(n1==n2 && n3>n4){
                             aux = vetord[c];
                             vetord[c]=vetord[i];
                             vetord[i]=aux;
                         }
                     }
                 }
                return vetord;   
            }
        
        
        public boolean fazerVenda(Date data,Carrinho carrinho){
            for(int i=0;i<vendas.length;i++){
                if(vendas[i]==null){
                    vendas[i] = Venda.getInstance(data);
                    if(vendas[i].VendaConfirmada(carrinho)){
                     vendas[i].valorVenda();
                     carrinho.esvaziar();
                     numVendas++;
                    return true;
                    }else{
                        return false;
                    }
                }
            }
            return false;
        }
	
        public boolean removerEstoque(int codigo,int quantidade){
            Produto produto = buscarInterno(codigo);
            if(produto!=null){
                if(produto.RemoverUnidade(quantidade)){
                    return true;
                }else
                return false;
            }else
            return false;
        }
        
        public Venda buscarVend(int codigo){
            Venda aux;
		for (int indice = 0; indice < this.produtos.length; indice++) {
			if (vendas[indice] != null && vendas[indice].getCodigo() == codigo) {
                                aux = new Venda(vendas[indice]);
                                
				return aux; 
			}
		}
		return null;
        }
        public Produto[] pegarItens(Venda vendas){
            Item itens[] = vendas.getItensVendidos();
            Produto aux[] = new Produto[itens.length];
            for(int i=0;i<itens.length;i++){
                if(itens[i]!=null){
                    aux[i] = itens[i].getProduto();
                    aux[i].setQuantNoEstoque(itens[i].getQuantidade());
                    aux[i].setPreco(itens[i].getPreco());
                }
            }
            return aux;
        }
        private Produto buscarInterno(int codigo){
            for (int indice = 0; indice < this.produtos.length; indice++) {
			if (produtos[indice] != null && produtos[indice].getCodigo() == codigo) {
                                return produtos[indice]; 
			}
		}
            return null;
        }
        public Produto buscarCodigo(int codigo){
            Produto aux;
		for (int indice = 0; indice < this.produtos.length; indice++) {
			if (produtos[indice] != null && produtos[indice].getCodigo() == codigo && produtos[indice].getEstado()== 1 ) {
                                aux = new Produto(produtos[indice]);
                                
				return aux; 
			}
		}
		return null;
        }
        public Produto buscar(int codigo) {
                Produto aux;
		for (int indice = 0; indice < this.produtos.length; indice++) {
			if (produtos[indice] != null && produtos[indice].getCodigo() == codigo) {
                                aux = new Produto(produtos[indice]);
                                
				return aux; 
			}
		}
		return null;
	}

	private Produto buscar(String nome) {
            for (int indice = 0; indice < this.produtos.length; indice++) {
			if (produtos[indice] != null && produtos[indice].getNome().equals(nome)) {
				return produtos[indice]; 
			}
		}
		return null;
	}
  //Duvida Aula de quinta   
        private Produto buscarVenda(int codigo){
            for (int indice = 0; indice < this.vendas.length; indice++) {
                  if(vendas[indice]!=null){  
                    Item[] produto = this.vendas[indice].getItensVendidos();
                    for (int x = 0; x < produto.length; x++){
                        if(produto[x]!=null){
                            if (vendas[indice] != null && produto[x].getProduto().getCodigo() == codigo) {
                                    return produtos[indice]; 
                            }
                        }
                    }
                  }
                    
             } 
            return null;
        }
        
      


}