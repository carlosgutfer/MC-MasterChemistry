package com.example.mc_masterchemistry.UI.tablero;


import android.content.Context;
import com.example.mc_masterchemistry.db.Entities.ElementoEntity;
import java.util.ArrayList;
import java.util.List;


public class CombinacionQuimica {
    private String mensaje;
    public CombinacionQuimica(){

    }
    private String elemento;
    private ArrayList<String> listaElementos;
    private ArrayList<Integer> compuestosids = new ArrayList<>();
    private int id1, id2;


    public boolean combinacionesMetal(boolean posible , int idM,int idNm, int idHuO,ArrayList<Integer> calculo){

        if (idM<10)
        {
            posible=true;
        }
        for (int i=0; i<calculo.size();i++) {
            if (calculo.get(i)==-1){
            if (idNm > 13&idHuO!=0 ) {
                 this.mensaje = "El NO del hidrógeno debe ser positivo con elementos de los grupos 16 y 17";
                posible = false;
                }
            }
        }
        if(idNm>9&idNm<13){
            for(int i=0;i<calculo.size();i++){
                if(calculo.get(i)==1){
                    posible=false;
                    this.mensaje= "El NO del hidrógeno debe ser negativo con elementos de los grupos 14 y 15";

                }
            }
        }

        return posible;
    }

    public String getMensaje(String error){
        error=mensaje;
        return  error;
    }



    public String ElementoList(List<ElementoEntity>allelementos,ArrayList<String> elementos,int idM,int idNm, int idHuO,int M, int NM, int HuO){

        if (M<2&NM<2&HuO<2) {
            if (idM < 10 & idNm > 9) {
                this.id1=idM;
                this.id2=idNm;
                elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idNm).getSimbolo();
            }
            else if (idM < 10 & idHuO == 13) {
                this.id1=idM;
                this.id2=idHuO;
                elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idHuO).getSimbolo();
            }
            else if (idM < 10 & idHuO == 17) {
                this.id1=idM;
                this.id2=idHuO;
                elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idHuO).getSimbolo();
            }
            else if (idNm > 17 & idHuO == 17) {
                this.id1=idHuO;
                this.id2=idNm;
                elemento = allelementos.get(idHuO).getSimbolo() + allelementos.get(idNm).getSimbolo();
            }
            else if (idNm > 13 & idHuO == 13) {
                this.id1=idHuO;
                this.id2=idNm;
                elemento = allelementos.get(idHuO).getSimbolo() + allelementos.get(idNm).getSimbolo();
            }
            else if (idNm > 9 & idNm < 13 & idHuO == 13) {
                this.id1=idNm;
                this.id2=idHuO;
                elemento = allelementos.get(idNm).getSimbolo() + allelementos.get(idHuO).getSimbolo();
            }
            else if (idNm > 9 & idNm < 17 & idHuO == 17) {
                this.id1=idHuO;
                this.id2=idNm;
                elemento = allelementos.get(idNm).getSimbolo() + allelementos.get(idHuO).getSimbolo();
            }
        }else {
            if (idM < 9 & idNm > 9) {
                this.id1=idM;
                this.id2=idNm;
                if (M > 1&NM<2) {
                    elemento = allelementos.get(idM).getSimbolo() + M + allelementos.get(idNm).getSimbolo();
                } else if (NM > 1&M<2) {
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idNm).getSimbolo() + NM;
                } else if (NM > 1 & M > 1) {
                    elemento = allelementos.get(idM).getSimbolo() +M+ allelementos.get(idNm).getSimbolo() + NM;
                }

            }
            else if (idM < 10 & idHuO == 13) {
                this.id1=idM;
                this.id2=idHuO;
                if (M > 1&HuO<2) {
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idHuO).getSimbolo();
                }else if (HuO>1&M<2){
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idHuO).getSimbolo()+HuO;
                }else if (HuO>1&M>1){
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idHuO).getSimbolo()+HuO;
                }
                    }
            else if (idM < 10 & idHuO == 17) {
                this.id1=idM;
                this.id2=idHuO;
                if (M > 1&HuO<2) {
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idHuO).getSimbolo();
                }else if (HuO>1&M<2){
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idHuO).getSimbolo()+HuO;
                }else if (HuO>1&M>1){
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idHuO).getSimbolo()+HuO;
                }

            }
            else if (idNm > 17 & idHuO == 17) {
                this.id1=idHuO;
                this.id2=idNm;
                if(NM>1&HuO<2){
                    elemento = allelementos.get(idHuO).getSimbolo() + allelementos.get(idNm).getSimbolo()+NM;}
                else if(HuO>1&NM<2){
                    elemento = allelementos.get(idHuO).getSimbolo()+HuO + allelementos.get(idNm).getSimbolo();
                }
                else if(NM>1&HuO>1){
                    elemento = allelementos.get(idHuO).getSimbolo()+HuO + allelementos.get(idNm).getSimbolo()+NM;
                }
            }
            else if (idNm > 13 & idHuO == 13) {
                this.id1=idHuO;
                this.id2=idNm;
                if(NM>1&HuO<2){
                    elemento = allelementos.get(idHuO).getSimbolo() + allelementos.get(idNm).getSimbolo()+NM;}
                else if(HuO>1&NM<2){
                    elemento = allelementos.get(idHuO).getSimbolo()+HuO + allelementos.get(idNm).getSimbolo();
                }
                else if(NM>1&HuO>1){
                    elemento = allelementos.get(idHuO).getSimbolo()+HuO + allelementos.get(idNm).getSimbolo()+NM;
                }

            }
            else if (idNm > 9 & idNm < 13 & idHuO == 13) {
                this.id1=idNm;
                this.id2=idHuO;
                if(NM>1&HuO<2){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idHuO).getSimbolo();
                } else if(HuO>1&NM<2){
                    elemento = allelementos.get(idNm).getSimbolo()+ allelementos.get(idHuO).getSimbolo()+HuO;
                }else if (HuO>1&NM>1){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idHuO).getSimbolo()+HuO;
                }

            }
            else if (idNm > 9 & idNm < 17 & idHuO == 17) {
                this.id1=idNm;
                this.id2=idHuO;
                if(NM>1&HuO<2){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idHuO).getSimbolo();
                } else if(HuO>1&NM<2){
                    elemento = allelementos.get(idNm).getSimbolo()+ allelementos.get(idHuO).getSimbolo()+HuO;
                }else if (HuO>1&NM>1){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idHuO).getSimbolo()+HuO;
                }
            }


        }

        return  elemento;
    }

    public int sumaMetal(int metal){
        metal++;
        return metal;
    }
    public int sumaNoMetal(int nometal){
        nometal++;
        return nometal;
    }
    public  int sumaHuO(int HuO){
        HuO++;
        return HuO;
    }

    public boolean comprobarElementoList(List<ElementoEntity>allelementos,ArrayList<String> elementos,int idM,int idNm, int idHuO,int M, int NM, int HuO,String compuesto,ArrayList<Integer>idsCompuestos){
        boolean valido=true;
        String elemento;

        elemento = ElementoList(allelementos,elementos,idM,idNm,idHuO,M,NM,HuO);
   if (compuesto.equals(elemento))
   {
       for (int i = 0; i < elementos.size(); i++) {
           if (elementos.get(i).equals(elemento)) {
               valido = false;

               this.mensaje = "El compuesto " + elemento + " ya ha sido creado.";
               break;
           } else {
               valido = true;
           }
       }
       if (valido == true) {
           elementos.add(elemento);
          idsCompuestos.add(id1);
          idsCompuestos.add(id2);
          this.compuestosids=idsCompuestos;
           this.listaElementos = elementos;
       }
   } else{
       valido = false;
       this.mensaje = "¡Upps! La formula introducida no es correcta. Revisa los símbolos, el orden (segun la secuencia IUPAC) y los subíndices.";
   }
        return  valido;
    }

    public ArrayList<String> getList(ArrayList<String> elementos){
        elementos=listaElementos;
        return elementos;
    }
    public ArrayList<Integer> getIds(ArrayList<Integer> ids){
        ids=compuestosids;
        return ids;
    }

    public boolean minElementos(ArrayList<Integer> calculo, int No, int suma){
        boolean min=true;

        if (calculo.size()>1) {
            if (No == suma) {
                for (int x = 0; x < calculo.size(); x++) {
                    for (int y = x + 1; y < calculo.size(); y++) {
                        if (calculo.get(x) == calculo.get(y) & calculo.get(x) != 0) {
                            if (calculo.get(x) == -No) {
                                min=false;
                                this.mensaje="¡Casi!Recuerda crear los compuestos con el menor número de elementos posibles";
                                break;
                            }
                        }
                    }
                }
            } else {
                min = true;
            }
        }
        return min;
    }



}