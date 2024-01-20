package org.drozdek.stacks.unlam;

import java.util.Calendar;
import java.util.Random;

import static java.lang.System.out;

public class PilaInteligente implements Pila {
        public static final char ESTATICA = 0;
        public static final char DINAMICA = 1;
        public static final int VALOR_CRITICO=2000;

        private Pila pila;
        private final char tipo;
        private int contadorApilar = 0;
        private int contadorDesapilar = 0;

        
        public PilaInteligente() {
                pila= new PilaEstatica();
                tipo=ESTATICA;
        }

        @Override
        public boolean apilar(Object obj) {
                convertirSiEsNecesario();
                pila.apilar(obj);
                contadorApilar++;
                return true;
        }

        @Override
        public Object desapilar() {
                convertirSiEsNecesario();
                Object obj=pila.desapilar();
                if(obj!=null){
                        contadorDesapilar++;
                }
                return obj;
        }

        @Override
        public boolean esVacia() {
                return pila.esVacia();
        }

        @Override
        public void vaciar() {
                pila.vaciar();
                convertirSiEsNecesario();
        }

        @Override
        public Object verTope() {
                return pila.verTope();
        }
        private void convertirSiEsNecesario() {
                if(tipo==ESTATICA&&esNecesarioCambioADinamico()){
                        cambiarADinamico();
                }else
                if(tipo==DINAMICA&&esNecesarioCambioAEstatico()){
                        cambiarAEstatico();
                }
        }

        private boolean esNecesarioCambioAEstatico() {
                
                return (contadorApilar-contadorDesapilar)<VALOR_CRITICO/2;
        }

        private void cambiarAEstatico() {
                PilaEstatica pilaE=new PilaEstatica();
                Object obj=null;
                while((obj=pila.desapilar())!=null){
                        pilaE.apilar(obj);
                }
                pila=pilaE;     
        }

        private void cambiarADinamico() {
                PilaDinamica pilaD=new PilaDinamica();
                Object obj=null;
                while((obj=pila.desapilar())!=null){
                        pilaD.apilar(obj);
                }
                pila=pilaD;             
        }

        private boolean esNecesarioCambioADinamico() {
                return (contadorApilar-contadorDesapilar)>VALOR_CRITICO;
        }


        /**
         * @param args
         */
        public static void main(String[] args) {
                 new PilaInteligente().test();
/*
                //Prueba apilar
                Pila p1 = new PilaDinamica();
                for(int i=0;i<60;i++){
                        p1.apilar(new Integer(i));
                }
                //Prueba esVacia
                out.println("Prueba esVacia:Resultado esperado:Resultado Esperado:false::"+p1.esVacia());
                //Prueba desapilar
                for(int i=0;i<60;i++){
                        p1.desapilar();
                }
                //Prueba  esVacia
                out.println("Prueba esVacia:Resultado esperado:Resultado Esperado:true::"+p1.esVacia());

                for(int i=0;i<70;i++){
                        p1.apilar(new Long(i));
                }
                //Prueba VerTope
                Object obj=null;
                boolean exito=true;
                while(!p1.esVacia()){
                        obj=p1.verTope();
                        obj=p1.verTope();
                        exito&=obj.equals(p1.desapilar());
                }
                out.println("Prueba VerTope:Resultado esperado:Resultado Esperado:true::"+exito);
                
                for(int i=0;i<45;i++){
                        p1.apilar(new Double(i));
                }
                p1.vaciar();
                //Prueba vaciar
                out.println("Prueba vaciar:Resultado esperado:Resultado Esperado:true::"+p1.esVacia());
*/
        }

        private void test() {
                Calendar ini;
                Calendar fin;

                int contadorApilar = 0;
                int contadorDesapilar = 0;
                
                int maximoIncremento = 5000000;
                int repeticiones = 1000;
                PilaDinamica pilad;
                PilaEstatica pilae;

                pilae = new PilaEstatica();
                pilad = new PilaDinamica();
                Random random = new Random();
                for (int j = 0; j < maximoIncremento; j+=1000) {
                        // variables para sacar promedios
                        long apilarEstaticoDemoras = 0;
                        long apilarDinamicoDemoras = 0;
                        long desapilarEstaticoDemoras = 0;
                        long desapilarDinamicoDemoras = 0;

                        for (int i = 0; i < repeticiones; i++) {
                                if (i % 2 == 0) {
                                        ini = Calendar.getInstance();   //System.currentTimeMillis();
                                        pilae.apilar(Integer.valueOf(random.nextInt()));
                                        fin = Calendar.getInstance();   //System.currentTimeMillis();
                                        apilarEstaticoDemoras += fin.getTimeInMillis() - ini.getTimeInMillis();

                                        ini = Calendar.getInstance();   //System.currentTimeMillis();
                                        pilad.apilar(Integer.valueOf(random.nextInt()));
                                        fin = Calendar.getInstance();   //System.currentTimeMillis();
                                        apilarDinamicoDemoras += fin.getTimeInMillis() - ini.getTimeInMillis();

                                        contadorApilar++;
                                } else {

                                        ini = Calendar.getInstance();   //System.currentTimeMillis();
                                        pilae.desapilar();
                                        fin = Calendar.getInstance();   //System.currentTimeMillis();
                                        desapilarEstaticoDemoras +=  fin.getTimeInMillis() - ini.getTimeInMillis();

                                        ini = Calendar.getInstance();   //System.currentTimeMillis();
                                        pilad.desapilar();
                                        fin = Calendar.getInstance();   //System.currentTimeMillis();
                                        desapilarDinamicoDemoras +=  fin.getTimeInMillis() - ini.getTimeInMillis();

                                        contadorDesapilar++;

                                }

                        }
                        for(int i=contadorApilar-contadorDesapilar;i<j;i++){
                                //incremento el tama�o de la pila
                                pilae.apilar(Integer.valueOf(random.nextInt()));
                                pilad.apilar(Integer.valueOf(random.nextInt()));
                                contadorApilar++;
                        }

                        //diferencias
                        StringBuffer mensaje=new StringBuffer("APILAR::Mas Eficiente::");
                        if(apilarDinamicoDemoras==apilarEstaticoDemoras){
                                mensaje.append("IGUALES("+apilarEstaticoDemoras+")");
                        }else if(apilarDinamicoDemoras>apilarEstaticoDemoras){
                                mensaje.append("ESTATICO");
                        }else{
                                mensaje.append("DINAMICO");
                        }
                        mensaje.append("                DESAPILAR::Mas Eficiente::");
                        if(desapilarDinamicoDemoras==desapilarEstaticoDemoras){
                                mensaje.append("IGUALES("+desapilarEstaticoDemoras+")");
                        }else if(desapilarDinamicoDemoras>desapilarEstaticoDemoras){
                                mensaje.append("ESTATICO");
                        }else{
                                mensaje.append("DINAMICO");
                        }

                        out.printf("%09d - %S - %d\n",j,mensaje,contadorApilar-contadorDesapilar);

                }
        }

}
