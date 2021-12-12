/*@Arthur Carvalho & Gabriel Santos*/
package loja;


import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Programa {
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		Sistema sis = Sistema.getInstance();
		sis.init();
		menu(sis);
	}

	static void menu(Sistema sis) {
		System.out.println("\n LOJA \n");
		System.out.println(" 0 - ADM");
		System.out.println(" 1 - Vendedor");
                int opcao = scn.nextInt();
		switch (opcao) {
                    case 0:
                        ADM(sis);
                        break;
                    case 1:
                        Carrinho(sis);
                        break;
                    default:
			System.out.println("\n\nNao foi possivel realizar a solicitacao.\n");
			System.out.println("TENTE NOVAMENTE!");
                        break;
                        
                }
                menu(sis);
	}
        static void ADM(Sistema sis){
                System.out.println("\n LOJA \n");
		System.out.println(" 0 - SAIR do ADM");
		System.out.println(" 1 - Inserir produtos");
		System.out.println(" 2 - Excluir produtos");
		System.out.println(" 3 - Alterar dados de um produto");
		System.out.println(" 4 - Gerar as listagens");
		System.out.print("Opcao?: ");

		int opcao = scn.nextInt();

		switch (opcao) {
		case 1:
                        if(sis.inserir(cadastrar())){
                            System.out.println("Produto Cadastrado");
                        }else{
                            System.out.println("Produto nao pode cadastrado");
                        }
			break;

		case 2:
                        System.out.println("Informe o codigo do produto para excluir");
                        int codigo = scn.nextInt();
			if(sis.excluir(codigo)){
                            System.out.println("Produto excluido");
                        }else{
                            System.out.println("Produto nao pode ser excluido");
                        }
			break;

		case 3:
			if(alterar()){
                            System.out.println("Produto Alterado");
                        }else
                            System.out.println("Produto nao pode ser alterado");
			break;

		case 4:
			listar(sis);
			break;

		case 0:
			System.out.println("\nAte mais!\n");
			return;

		default:
			System.out.println("\n\nNao foi possivel realizar a solicitacao.\n");
			System.out.println("TENTE NOVAMENTE!");
			
		}
                ADM(sis);
        }
       
        static void Carrinho(Sistema sis){
            Carrinho carrinho = Carrinho.getInstance();
            System.out.println("\n LOJA \n");
            System.out.println("\n\nComprar\n");
            System.out.println(" 0 - Sair das vendas e esvaziar carrinho");
            System.out.println(" 1 - Adicionar um item");
            System.out.println(" 2 - Remover um item");
            System.out.println(" 3 - finalizar compra");
            System.out.print("Insira o codigo da acao que deseja executar: ");
                
            int opcao = scn.nextInt();
            switch (opcao) 
            {
                case 0:
                    carrinho.esvaziar();
                    menu(sis);
                case 1: 
                    System.out.println("Digite o codigo do produto que deseja inserir: ");
                    int codigo = scn.nextInt();
                    System.out.println("Digite o preco do produto que deseja inserir: ");
                    double preco = scn.nextDouble();
                    System.out.println("Digite a quantidade do produto que deseja inserir: ");
                    int quantidade= scn.nextInt();
                    if(carrinho.additem(codigo,preco,quantidade))
                    {
                        System.out.println("Produto inserido no carrinho");
                    }else{
                        System.out.println("Produto nao pode ser inserido");
                    }
                    break;
                case 2:
                    System.out.println("digite o codigo do produto que deseja remover do carrinho: ");
                    codigo = scn.nextInt();
                    System.out.println("Digite a quantidade do produto que deseja remover: ");
                    quantidade = scn.nextInt();
                    if(carrinho.removeritem(codigo,quantidade))
                    {
                        System.out.println("Produto removido do carrinho");
                    }else{
                        System.out.println("Produto nao pode ser removido");
                    }
                    break;
                case 3:
                    System.out.println("A compra será finalizada");
                    System.out.println("digite a data da compra ");
                    Date data = new Date();
                    if(sis.fazerVenda(data,carrinho))
                    {
                        System.out.println("A venda foi realizada");
                        menu(sis);
                    }else{
                        System.out.println("A venda não foi realizada");
                    }
                    
                    break;
                default:
                    System.out.println("\nCodigo inserido eh invalido! Retornando...\n");		
		}
                Carrinho(sis);
            }
         

	static Produto cadastrar() {
		System.out.println("\n\nCADASTRAR PRODUTO\n");

		System.out.print("Nome: ");
		String nome = scn.next();

		System.out.print("Marca: ");
		String marca = scn.next();

		System.out.print("Preco: ");
		double preco = scn.nextDouble();

		System.out.print("Quantidade: ");
		int quantidade = scn.nextInt();
                
                Produto p1 = Produto.getInstance(nome,marca,preco,quantidade);
                return p1;

	}

	static boolean alterar() {
                Sistema sis = Sistema.getInstance();
                System.out.println("Informe o codigo do produto para alterar");
                int codigo = scn.nextInt();
                Produto p = sis.buscar(codigo);
                
                System.out.println("\nALTERAR PRODUTO\n");
		System.out.println(" 1 - Nome do produto");
		System.out.println(" 2 - Marca do produto");
		System.out.println(" 3 - Preco do produto");
		System.out.println(" 4 - Quantidade em estoque\n");
		System.out.print("Insira o codigo da acao que deseja executar: ");

		int opcao = scn.nextInt();

		String alteracao;
		boolean resposta;

		switch (opcao) {
		case 1:
			System.out.print("Alterar (NOME) de " + p.getNome()
					+ " para: ");
			alteracao = scn.next();

			resposta = sis.alterarProduto(codigo, opcao,alteracao);
			break;

		case 2:
			System.out.print("Alterar (MARCA) de "
					+ p.getMarca() + " para: ");
			alteracao = scn.next();

			resposta = sis.alterarProduto(codigo, opcao,alteracao);
			break;

		case 3:
			System.out.print("Alterar (VALOR) de R$ "+ Double.toString(p.getPreco())+ " para: R$");
			double novoPreco = scn.nextDouble();
			alteracao = Double.toString(novoPreco);

			resposta = sis.alterarProduto(codigo, opcao,alteracao);
			break;

		case 4:
			System.out.print("Alterar (QUANTIDADE EM ESTOQUE) de "
					+ Integer.toString(p.getQuantidade())
					+ " para: ");
			alteracao = scn.next();

			resposta = sis.alterarProduto(codigo, opcao,alteracao);
			break;

		default:
			System.out.println("Nao foi possivel realizar a solicitacao.");
			return false;
		}

		if (resposta == true)
			return true;
		else
			return false;
	}

	static void listar(Sistema sis) {
            Produto aux2[];
            Venda aux[];
		System.out.println("\n\nCENTRAL DE CONTROLE - Listagem\n");
		System.out.println(" 1 - Detalhar um produto");
		System.out.println(" 2 - Exibir todos os produtos (ORDEM DE CADASTRO)");
		System.out.println(" 3 - Exibir todos os produtos (ORDEM ALFABETICA)");
		System.out.println(" 4 - Exibir todas as vendas");
                System.out.println(" 5 - Exibir venda de um codigo");
		System.out.println(" 6 - Sair do sistema\n");
		System.out.print("Insira o codigo da acao que deseja executar: ");

		int opcao = scn.nextInt();

		switch (opcao) {
		case 1:
                        System.out.print("Insira o codigo do produto que deseja : ");
                        int codigo = scn.nextInt();
                        Produto aux1[] = new Produto[1] ;
                        aux1[0] = sis.buscarCodigo(codigo);
                        if(aux1[0]!=null){
                            odernarTabela(aux1);
                        }else
                           System.out.println("Produto não existe");
			
                        break;


		case 2:
                        aux2 = sis.buscarOrdemCadastro();
                        odernarTabela(aux2);
			break;

		case 3:
			aux2 = sis.Ordemalf();
			odernarTabela(aux2);
			break;

		case 4:
			aux = sis.todasVendas();
                        tabelaVendas(aux);
			break;
                case 5:
                        System.out.print("Insira o codigo da venda que deseja : ");
                        int codigoVenda = scn.nextInt();
                        Venda vend[] = new Venda[1] ;
                        Produto itens[];
                        vend[0] = sis.buscarVend(codigoVenda);
                        if(vend[0]!=null){
                            tabelaVendas(vend);
                            System.out.println("Produtos Comprados nessa venda");
                            itens = sis.pegarItens(vend[0]);
                            odernarTabela(itens);
                        }else
                           System.out.println("Produto não existe");
			
                    break;
		case 6:
			System.out.println("\nAte mais!\n");
			break;

		default:
			System.out.println(
					"\nCodigo inserido eh invalido! Retornando para o menu...\n");
			//menu(sis, x);
		}
	}
        
       static private void odernarTabela(Produto produtos[]){
            int maiorNome = 0;
            int maiorMarca= 0;
            String nome = "Nome";
            String marca = "Marca";
            int nomeN = nome.length(); 
            int marcaN = marca.length();
                for(int x=0;x<produtos.length;x++){
                    if(produtos[x]!=null){
                        if(maiorNome<produtos[x].getNome().length()){
                            maiorNome=produtos[x].getNome().length(); 
                        }
                        if(maiorMarca<produtos[x].getMarca().length()){
                            maiorMarca = produtos[x].getMarca().length();
                        }
                    }
                }
                for(int x=0;x<produtos.length;x++){
                    if(produtos[x]!=null){
                        if(maiorNome>produtos[x].getNome().length()){
                            int diferenca = maiorNome-produtos[x].getNome().length();
                            for(int j=0;j<diferenca;j++){
                                produtos[x].setNome(produtos[x].getNome()+" ");
                            }
                        }
                        if(maiorMarca>produtos[x].getMarca().length()){
                            int diferenca = maiorMarca-produtos[x].getMarca().length();
                            for(int j=0;j<diferenca;j++){
                               produtos[x].setMarca( produtos[x].getMarca()+" ");
                            }
                        }
                    }
                }
                int diferencaN = maiorNome-nomeN;
                for(int j=0;j<diferencaN;j++){
                    nome = nome+" ";
                }
                int diferencaM = maiorMarca-marcaN;
                for(int j=0;j<diferencaM;j++){
                    marca = marca+" ";
                }
                gerarTabela(produtos,nome,marca);
        }
       static public void gerarTabela(Produto produtos[],String nome,String marca){
           
           System.out.println("Codigo  | "+nome+" | "+marca+" | "+"Preco  | Quantidade  |");
           for(int indice=0;indice<produtos.length;indice++){
               if(produtos[indice]!=null){
               System.out.print(produtos[indice].getCodigo()+"       | "+produtos[indice].getNome()+" | "+produtos[indice].getMarca()+" |  ");
               System.out.printf("%.2f",produtos[indice].getPreco());
               System.out.print("  | "+produtos[indice].getQuantidade()+"\n");    
               }
           }
               
      }
       static public void tabelaVendas(Venda vendas[]){
           System.out.println("Codigo\t| Data\t\t| Valor Total ");
           for(int i=0;i<vendas.length;i++){
               if(vendas[i]!=null){
                   System.out.println(vendas[i].getCodigo()+"\t| "+vendas[i].getData()+"   | "+vendas[i].getValorTotal());
               }
           }
       }

}
