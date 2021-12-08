/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja;

/**
 *
 * @author Gabriel Santos
 */
public class Carrinho {
    private Item[] itens;
    
    private static Carrinho instance;
     
    private Carrinho()
    {
        itens = new Item[20];
    }
    public static Carrinho getInstance()
    {
        if(instance == null)
        {
           instance = new Carrinho(); 
        }
            return instance;
    }

    
    public boolean additem(int codigo,double preco,int quantidade)
    {
        Sistema sis = Sistema.getInstance();
        Produto produto = sis.buscar(codigo);
         for(int i=0;i<itens.length;i++)
         {
             if(itens[i]!=null){
                if(codigo == itens[i].getProduto().getCodigo())
                {
                    if((itens[i].getQuantidade()+quantidade)<=produto.getQuantidade()){
                    itens[i].setQuantidade(itens[i].getQuantidade() + quantidade);
                    itens[i].setPreco(itens[i].getPreco() + itens[i].getProduto().getPreco());
                    return true;
                    }else
                        return false;
                }
                
             }  
         }
         for(int i=0;i<itens.length;i++){ 
             
                   if(itens[i]== null)
                    { 
                       if(produto.getQuantidade()>=quantidade){
                         itens[i] = Item.getInstance(produto,preco,quantidade);
                         return true;
                       }else
                         return false;
                       
                    }
         }
        return false;
    }
    
    public boolean removeritem(int codigo,int quantidade)
    {
        Sistema sis = Sistema.getInstance();
        for(int i=0;i<itens.length;i++)
        {
            if(itens[i]!=null){
                if(codigo == itens[i].getProduto().getCodigo() && conferirestoq(codigo,quantidade))
                 {
                     if(itens[i].getQuantidade()-quantidade==0){
                         itens[i]=null;
                         return true;
                     }
                     itens[i].setQuantidade(itens[i].getQuantidade()-quantidade);
                     return true;
                 }
                return false;
            }
        }
        return false;
    }
    
    public boolean conferirestoq(int codigo,int quantidade)
    {
        for(int i=0;i<itens.length;i++)
        {
           if(itens[i] != null){
                if(codigo == itens[i].getProduto().getCodigo())
                  {
                     if(itens[i].getProduto().getQuantidade()>= quantidade)
                     {
                         return true;
                     }else{
                         return false;
                     }
                  }
           }
        }
        return false;
    }
    public void esvaziar(){
        for(int i=0;i<itens.length;i++){
            itens[i]=null;
        }
    }
    
    public Item[] getItens()
    {
        Item aux[] = new Item[itens.length];
        for(int i=0;i<itens.length;i++){
            if(itens[i]!=null){
                aux[i] = new Item(itens[i]);
            }
        }
        return aux;
    }
}
