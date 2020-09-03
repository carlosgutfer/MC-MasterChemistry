package com.example.mc_masterchemistry.UI.terciarios;

import android.content.Context;


import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.ArrayList;
import java.util.List;

public class CombinacionQuimiciaTeryBi {
    private String mensaje;
    public CombinacionQuimiciaTeryBi(Context ctx){

    }
    private String elemento;
    private ArrayList<String> listaElementos;
    private ArrayList<Integer> compuestosids = new ArrayList<>();
    private int id1, id2,id3;
    private int numH, numO;

    public boolean combinacionesPosibles(boolean posible , int idM,int idNm, int idH, int idO,ArrayList<Integer> calculo){



        if (idM<10&idNm<10&idO==17&idH==13){
            int O=0;  int H=0;
            for (int i=0;i<calculo.size();i++){
                    if( calculo.get(i)==-1|calculo.get(i)==1){
                        H++;
                    }
                    else if(calculo.get(i)==-2){
                        O++;
                    }
            }
            if (H==O){
            posible=true;}
            else{posible=false;
            this.mensaje="¡Casi! Recuerda que para hacer un hidróxido el OH- es un anión poliatómico cuya carga global es de -1";}
        }



        for (int i=0; i<calculo.size();i++) {
            if (calculo.get(i)==-1){
                if (idNm > 13&idH!=0&idO!=0 ) {
                    this.mensaje = "El NO del hidrógeno debe ser positivo con elementos de los grupos 16 y 17";
                    posible = false;
                }
            }
        }
        if(idNm>9&idNm<13&idO!=17){
            for(int i=0;i<calculo.size();i++){
                if(calculo.get(i)==1){
                    posible=false;
                    this.mensaje= "El NO del hidrógeno debe ser negativo con elementos de los grupos 14 y 15";

                }
            }
        }
        if(idNm>9&idO==17&idH==13){
            for(int i=0;i<calculo.size();i++){
                if(calculo.get(i)==-1){
                    posible=false;
                    this.mensaje= "¡Casi! Recuerda que cuando crees un oxoácido usar el hidrogeno positivo";

                }
            }

        }

        return posible;
    }

    public String getMensaje(String error){
        error=mensaje;
        return  error;
    }



    private String ElementoList(List<ElementoEntity> allelementos, int idM, int idNm, int idH,int idO, int M, int NM, ArrayList<Integer> calculo,int numOxo){

        if (numOxo == 0) {
            sumaHuO(calculo);
        }
        if (M<2&NM<2&numH<2&numO<2&numOxo<2) {
            if (idM < 10 & idNm > 9&idO!=17&idH!=13) {
                this.id1=idM;
                this.id2=idNm;
                this.id3=-1;
                elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idNm).getSimbolo();
            }
            else if (idM < 10 & idH == 13&idO!=17) {
                this.id1=idM;
                this.id2=idH;
                this.id3=-1;
                elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idH).getSimbolo();
            }
            else if (idM < 10 & idO == 17&idH!=13&idNm<10) {
                this.id1=idM;
                this.id2=idO;
                this.id3=-1;
                elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idO).getSimbolo();
            }
            else if (idNm > 17 & idO == 17&idH!=13&idM>9) {
                this.id1=idO;
                this.id2=idNm;
                this.id3=-1;

                elemento = allelementos.get(idO).getSimbolo() + allelementos.get(idNm).getSimbolo();
            }
            else if (idNm > 13 & idH == 13&idO!=17) {
                this.id1=idH;
                this.id2=idNm;
                this.id3=-1;
                elemento = allelementos.get(idH).getSimbolo() + allelementos.get(idNm).getSimbolo();
            }
            else if (idNm > 9 & idNm < 13 & idH == 13&idO!=17) {
                this.id1=idNm;
                this.id2=idH;
                this.id3=-1;
                elemento = allelementos.get(idNm).getSimbolo() + allelementos.get(idH).getSimbolo();
            }
            else if (idNm > 9 & idNm < 17 & idO == 17&idH!=13&idM>9) {
                this.id1=idO;
                this.id2=idNm;
                this.id3=-1;
                elemento = allelementos.get(idNm).getSimbolo() + allelementos.get(idO).getSimbolo();
            }
            else if (idM<10&idH==13&idO==17){
                this.id1=idM;
                this.id2=idO;
                this.id3=idH;
                elemento= allelementos.get(idM).getSimbolo()+allelementos.get(idO).getSimbolo() + allelementos.get(idH).getSimbolo();

            }
            else if(idNm>9& idH == 13&idO==17){
                this.id1=idH;
                this.id2=idNm;
                this.id3=idO;
                elemento= allelementos.get(idH).getSimbolo()+allelementos.get(idNm).getSimbolo() + allelementos.get(idO).getSimbolo();
            }
            else if(idNm>9&idM<10&idO==17){
                this.id1=idM;
                this.id2=idNm;
                this.id3=idO;
                elemento= allelementos.get(idM).getSimbolo()+allelementos.get(idNm).getSimbolo() + allelementos.get(idO).getSimbolo();
            }
        }else {
            if (idM < 10 & idNm > 9&idO!=17) {
                this.id1=idM;
                this.id2=idNm;
                this.id3=-1;
                if (M > 1&NM<2) {
                    elemento = allelementos.get(idM).getSimbolo() + M + allelementos.get(idNm).getSimbolo();
                } else if (NM > 1&M<2) {
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idNm).getSimbolo() + NM;
                } else if (NM > 1 & M > 1) {
                    elemento = allelementos.get(idM).getSimbolo() +M+ allelementos.get(idNm).getSimbolo() + NM;
                }

            }
            else if (idM < 10 & idH == 13&idO!=17) {
                this.id1=idM;
                this.id2=idH;
                this.id3=-1;
                if (M > 1&numH<2) {
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idH).getSimbolo();
                }else if (numH>1&M<2){
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idH).getSimbolo()+numH;
                }else if (numH>1&M>1){
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idH).getSimbolo()+numH;
                }
            }
            else if (idM < 10 & idO == 17&idH!=13&idNm<10) {
                this.id1=idM;
                this.id2=idO;
                this.id3=-1;
                if (M > 1&numO<2) {
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idO).getSimbolo();
                }else if (numO>1&M<2){
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idO).getSimbolo()+numO;
                }else if (numO>1&M>1){
                    elemento = allelementos.get(idM).getSimbolo()+ M + allelementos.get(idO).getSimbolo()+numO;
                }

            }
            else if (idNm > 17 & idO == 17&idH!=13&idM>9) {
                this.id1=idO;
                this.id2=idNm;
                this.id3=-1;
                if(NM>1&numO<2){
                    elemento = allelementos.get(idO).getSimbolo() + allelementos.get(idNm).getSimbolo()+NM;}
                else if(numO>1&NM<2){
                    elemento = allelementos.get(idO).getSimbolo()+numO+ allelementos.get(idNm).getSimbolo();
                }
                else if(NM>1&numO>1){
                    elemento = allelementos.get(idO).getSimbolo()+numO + allelementos.get(idNm).getSimbolo()+NM;
                }
            }
            else if (idNm > 13 & idH == 13&idO!=17&idM>9) {
                this.id1=idH;
                this.id2=idNm;
                this.id3=-1;
                if(NM>1&numH<2){
                    elemento = allelementos.get(idH).getSimbolo() + allelementos.get(idNm).getSimbolo()+NM;}
                else if(numH>1&NM<2){
                    elemento = allelementos.get(idH).getSimbolo()+numH + allelementos.get(idNm).getSimbolo();
                }
                else if(NM>1&numH>1){
                    elemento = allelementos.get(idH).getSimbolo()+numH + allelementos.get(idNm).getSimbolo()+NM;
                }

            }
            else if (idNm > 9 & idNm < 13 & idH == 13&idO!=17) {
                this.id1=idNm;
                this.id2=idH;
                this.id3=-1;
                if(NM>1&numH<2){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idH).getSimbolo();
                } else if(numH>1&NM<2){
                    elemento = allelementos.get(idNm).getSimbolo()+ allelementos.get(idH).getSimbolo()+numH;
                }else if (numH>1&NM>1){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idH).getSimbolo()+numH;
                }

            }
            else if (idNm > 9 & idNm < 17 & idO == 17&idH!=13&idM>9) {
                this.id1=idNm;
                this.id2=idO;
                this.id3=-1;
                if(NM>1&numO<2){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idO).getSimbolo();
                } else if(numO>1&NM<2){
                    elemento = allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo()+numO;
                }else if (numO>1&NM>1){
                    elemento = allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idO).getSimbolo()+numO;
                }
            }
            else if (idM < 10 & idH == 13&idO==17) {
                this.id1 = idM;
                this.id2 = idH;
                this.id3 = idO;
              if (numH > 1 & M < 2) {
                    elemento = allelementos.get(idM).getSimbolo() + "("+ allelementos.get(idO).getSimbolo() + allelementos.get(idH).getSimbolo() + ")"+ numH;
                }
            }
            else if(idNm>9& idH == 13&idO==17){
                this.id1=idH;
                this.id2=idNm;
                this.id3=idO;
                if(NM>1&numO<2&numH<2) {
                    elemento = allelementos.get(idH).getSimbolo() + allelementos.get(idNm).getSimbolo()+NM + allelementos.get(idO).getSimbolo();
                }else if(numO>1&NM<2&numH<2){
                    elemento = allelementos.get(idH).getSimbolo() + allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo()+numO;
                }else if(numH>1&NM<2&numO<2){
                    elemento = allelementos.get(idH).getSimbolo()+numH + allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo();
                }else if(numO>1&NM>1&numH<2){
                    elemento = allelementos.get(idH).getSimbolo() + allelementos.get(idNm).getSimbolo()+NM+ allelementos.get(idO).getSimbolo()+numO;
                }else if(numH>1&NM<2&numO>1){
                    elemento = allelementos.get(idH).getSimbolo()+numH + allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo()+numO;
                }

            }
            else if(idNm>9&idM<10&idO==17){
                this.id1=idM;
                this.id2=idNm;
                this.id3=idO;
                if (M > 1&numOxo<2) {
                    elemento = allelementos.get(idM).getSimbolo()+M+ allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo();

                }else if(M<2&numOxo>1){
                    elemento = allelementos.get(idM).getSimbolo() + allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo()+numOxo;

                }else if(M>1&numOxo>1){
                    elemento = allelementos.get(idM).getSimbolo()+M + allelementos.get(idNm).getSimbolo()+ allelementos.get(idO).getSimbolo()+numOxo;

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
    private void sumaHuO(ArrayList<Integer> calculo){
        this.numO=0; this.numH=0;
        for (int i=0; i<calculo.size();i++){
            if( calculo.get(i)==-1|calculo.get(i)==1){
               this.numH++;
            }
              else if(calculo.get(i)==-2){
                  this.numO++;
            }
        }


    }

    public boolean comprobarElementoList(String elemento,List<ElementoEntity>allelementos,ArrayList<String> elementos,int idM,int idNm, int idH,int idO,int M, int NM, ArrayList<Integer> calculo,String compuesto,ArrayList<Integer>idsCompuestos,int numOxo){
        boolean valido=true;
        elemento=ElementoList(allelementos,idM,idNm,idH,idO,M,NM,calculo,numOxo);

        if (compuesto.equals(elemento)) {
            for (int i = 0; i < elementos.size(); i++) {
                if (elementos.get(i).equals(elemento)) {
                    valido = false;

                    this.mensaje = "El compuesto " + elemento + " ya ha sido creado.";
                    break;
                } else {
                    valido = true;
                }
            }
            if (valido) {
                elementos.add(elemento);
                idsCompuestos.add(id1);
                idsCompuestos.add(id2);
                idsCompuestos.add(id3);
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
            }
        }


            int H=0, O=0;
            for (int i=0;i<calculo.size();i++){
                if( calculo.get(i)==-1|calculo.get(i)==1){
                    H++;
                }
                else if(calculo.get(i)==-2){
                    O++;
                }
            }
        if (H==O&H==suma+No){
            min=false;
            this.mensaje="¡Casi!Recuerda crear los compuestos con el menor número de elementos posibles";
        }

        return min;
    }

    public boolean elementoRepetido(ArrayList<String> elementosCreados,String compuestoModificar){
        boolean valido=true;
        for (int x = 0; x < elementosCreados.size(); x++) {
            if (elementosCreados.get(x).equals(compuestoModificar)) {
                this.mensaje="El elemento "+compuestoModificar+" ya ha sido creado";
                valido=false;
                break;
            }
        }
        return  valido;
    }


}
