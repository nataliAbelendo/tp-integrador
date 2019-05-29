package isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioBusqueda<E extends Comparable<E>> extends Arbol<E> {

	protected Arbol<E> izquierdo;
	protected Arbol<E> derecho;
	
	public ArbolBinarioBusqueda(){
		this.valor=null;
		this.izquierdo=new ArbolVacio<E>();
		this.derecho=new ArbolVacio<E>();
	}
	
	public ArbolBinarioBusqueda(E e){
		this.valor=e;
		this.izquierdo=new ArbolVacio<E>();
		this.derecho=new ArbolVacio<E>();
	}
	
	public ArbolBinarioBusqueda(E e,Arbol<E> i,Arbol<E> d){
		this.valor=e;
		this.izquierdo=i;
		this.derecho=d;
	}
	
	@Override
	public List<E> preOrden() {
		List<E> lista = new ArrayList<E>();
		lista.add(this.valor);
		lista.addAll(this.izquierdo.preOrden());
		lista.addAll(this.derecho.preOrden());
		return lista;
	}
	@Override
	public List<E> inOrden() {
		List<E> lista = new ArrayList<E>();
		lista.addAll(this.izquierdo.preOrden());
		lista.add(this.valor);
		lista.addAll(this.derecho.preOrden());
		return lista;
	}
	@Override
	public List<E> posOrden() {
		List<E> lista = new ArrayList<E>();
		lista.addAll(this.izquierdo.preOrden());
		lista.addAll(this.derecho.preOrden());
		lista.add(this.valor);
		return lista;

	}
	@Override
	public boolean esVacio() {
		return false;
	}
        
	@Override
	public E valor() {
		return this.valor;
	}
	
	@Override
	public Arbol<E> izquierdo() {
		return this.izquierdo;
	}
	
	@Override
	public Arbol<E> derecho() {
		return this.derecho;
	}


	@Override
	public void agregar(E a) {
		if(this.valor.compareTo(a)<1) {
			if (this.derecho.esVacio()) this.derecho = new ArbolBinarioBusqueda<E>(a);
			else this.derecho.agregar(a);
		}else {
			if (this.izquierdo.esVacio()) this.izquierdo= new ArbolBinarioBusqueda<E>(a);
			else this.izquierdo.agregar(a);
		}
	}
	
	@Override
	public boolean equals(Arbol<E> unArbol) {
		return this.valor.equals(unArbol.valor()) && this.izquierdo.equals(unArbol.izquierdo()) && this.derecho.equals(unArbol.derecho());
	}

	@Override
	public boolean contiene(E unValor) {
		// --HECHO-- TODO 1.a
		boolean bandera= false;
		if(this.valor== unValor) bandera=true;
		else {
			
			if(this.izquierdo().contiene(unValor)) bandera=true;
			else if(this.derecho().contiene(unValor))bandera=true;
		}
		return bandera;
	}

	@Override
	public int profundidad() {
		// --HECHO-- TODO 1.b
		
		int alturaI, alturaD,profundidad;
		if(this.esVacio()) profundidad=0;
		else{
		alturaI=1+ this.izquierdo().profundidad();
		alturaD=1+ this.derecho().profundidad(); 
		
		if(alturaI>alturaD) profundidad= alturaI;
		else if(alturaI==alturaD) profundidad=alturaI;
		else profundidad = alturaD;
		}
		return profundidad;
	}

	@Override
	public int cuentaNodosDeNivel(int nivel) {
		// ---hecho-- TODO 1.c
		int retorno;
		
		if( nivel== 0 ) retorno=1;
		else {
			if(this.derecho()==null && this.izquierdo==null )
			    retorno=0;
			else if(this.derecho()==null && this.izquierdo!=null)
				retorno= this.izquierdo().cuentaNodosDeNivel(nivel-1);
			else if(this.derecho()!=null && this.izquierdo==null)
				retorno= this.derecho().cuentaNodosDeNivel(nivel-1);
			else	
			retorno=this.derecho().cuentaNodosDeNivel(nivel-1)+this.izquierdo().cuentaNodosDeNivel(nivel-1);
		}
		
		return retorno;
	}

	@Override
	public boolean esCompleto() {
		// --hecho-- TODO 1.d
		boolean retorno=false;
	
		if (this.derecho==null){
			retorno=true;
		}
		else if(this.derecho.esVacio() &&(this.izquierdo().esVacio() || this.izquierdo().esCompleto())){
			retorno=true;
		}
		else{
			this.izquierdo().esCompleto();
			this.derecho().esCompleto();
		}
		
		
		return retorno;
	}

	@Override
	public boolean esLleno() {
		//--hecho-- TODO 1.e
		boolean retorno=true;
		int nivelHojas= this.profundidad();
		
		for(int i=0; i<nivelHojas && retorno;i++){
			if (2 == this.derecho().cuentaNodosDeNivel(i) && 2 == this.izquierdo().cuentaNodosDeNivel(i))
            retorno=true;	
			else retorno=false;
		}
		
		return retorno;
	}

}
