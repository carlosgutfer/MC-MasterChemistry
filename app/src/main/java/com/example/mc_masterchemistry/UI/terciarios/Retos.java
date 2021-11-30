package com.example.mc_masterchemistry.UI.terciarios;

import android.content.Context;


import com.example.mc_masterchemistry.db.Entities.ElementoEntity;

import java.util.ArrayList;
import java.util.List;


public class Retos {
   public static int numeroTipo = 5;


    public Retos(){


    }

    public String TipoReto(){
        String retoSt = "";
        int azar;
        if(numeroTipo>4) {

            azar = (int) Math.floor(Math.random() * 5);
            if (tablero_terciarios.retoH==0&tablero_terciarios.retoH2O==0){
                while(azar ==3| azar ==1){
                    azar = (int) Math.floor(Math.random() * 5);
                }

            }
            if (tablero_terciarios.retoH==1&tablero_terciarios.retoH2O==0){
                while(azar ==1) {
                    azar = (int) Math.floor(Math.random() * 5);
                }
            }
            if (tablero_terciarios.retoH==0&tablero_terciarios.retoH2O==2){
                while(azar ==3) {
                    azar = (int) Math.floor(Math.random() * 5);
                }
            }


            }else {
           azar =numeroTipo;}
            switch (azar) {
                case 0:
                    retoSt = " \n¡Comodín!, Puedes elegir un elemento de la lista con su NO. Combina los hidrogenos y oxígenos y escribelo directamente junto a ellos en la zona de crear."
                            +"\n\n"+ "      Fe  Co  Ni  Cu  Pd"+"\n\n"+"       Pt  Au  Hg  Tl  Sn"+"\n\n"+"      P  As  Sb  S  Se"+"\n\n"+"        Te  Cl  Br  I  At"+"\n";
                    numeroTipo=0;
                    break;
                case 1:
                    retoSt = "Suma una molécula de agua a cualquier óxido de no metal o halogenuro de oxigeno, de los que  ya hayas creado. Escribelo en la zona de compuestos y fusiona. ";
                    numeroTipo=1;
                    break;
                case 2:
                    retoSt = " Utiliza  los  cuatro elementos de oxígeno e hidrógeno (positivo o negativo) o pierdes 3 gemas. ";
                    numeroTipo=2;
                    break;
                case 3:
                    retoSt = "Sustituye un H+ que este combinado con un No Metal por Au+, de los que ya hayas creado. Escribelo en la zona de compuestos y fusiona.";
                   numeroTipo=3;
                    break;
                case 4:
                    numeroTipo=4;
                    retoSt = "Duplica cualquier carta de tus elementos para formar un nuevo compuesto";

                    break;
            }

        return retoSt;
    }


    public boolean retotodos(ArrayList<Integer> calculo){
        boolean todos=true;
        if(calculo.size()!=4){
            todos=false;
        }
        for (int i=0; i<calculo.size();i++){
            if(calculo.get(i)==0){
                todos=false;
            }
        }
        return todos;
    }

    Integer comodin(List<ElementoEntity> allElementos, String simbolo){
        int id=-1;
        for(int i=0; i<allElementos.size();i++){
            if(i!=13&i!=17) {
                if (simbolo.contains(allElementos.get(i).getSimbolo())){
                    id=allElementos.get(i).getId();
                }
            }
        }

        return id;
    }

    Integer sumaNoComodin( List<ElementoEntity> allElementos,int idM,int idNm, int suma){
        int NO=0;
        if(idM<10) {
            if (allElementos.get(idM).getNO1() + suma == 0) {
                NO = allElementos.get(idM).getNO1();
            } else if (allElementos.get(idM).getNO2() + suma == 0) {
                NO = allElementos.get(idM).getNO2();
            } else if (allElementos.get(idM).getNO3() + suma == 0) {
                NO = allElementos.get(idM).getNO3();
            } else if (allElementos.get(idM).getNO4() + suma == 0) {
                NO = allElementos.get(idM).getNO4();
            }
        }else if (idNm>9){
            if (allElementos.get(idNm).getNO1() + suma == 0) {
                NO = allElementos.get(idNm).getNO1();
            } else if (allElementos.get(idNm).getNO2() + suma == 0) {
                NO = allElementos.get(idNm).getNO2();
            } else if (allElementos.get(idNm).getNO3() + suma == 0) {
                NO = allElementos.get(idNm).getNO3();
            } else if (allElementos.get(idNm).getNO4() + suma == 0) {
                NO = allElementos.get(idNm).getNO4();
            }
        }
        return  NO;
    }

    ArrayList<String>  compuestosCreados(String compuesto,ArrayList<Integer> idsCompuestosCreados, List<ElementoEntity> allElementos, ArrayList<String> compuestosStringCreados) {
        int position = 0;
        String igual;
        for (int i = 0; i < idsCompuestosCreados.size(); i = i + 3) {
            if (idsCompuestosCreados.get(i) == 17 & idsCompuestosCreados.get(i + 1) > 9 & idsCompuestosCreados.get(i + 2) == -1) {
                if (compuestosStringCreados.get(position).equals("O" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo())) {
                    igual = "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O2";
                    if (igual.equals(compuesto)) {
                        compuestosStringCreados.set(position, "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O2");
                    }
                } else if (compuestosStringCreados.get(position).equals("O2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo())) {
                    igual = "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O3";
                    if (igual.equals(compuesto)) {
                        compuestosStringCreados.set(position, "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O3");}

                } else if(compuestosStringCreados.get(position).equals(allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo()+"O2" )){
                        igual = "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O3";if (igual.equals(compuesto)) {
                        if (igual.equals(compuesto)) {
                            compuestosStringCreados.set(position, "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O3");}
                    }
                } else if (compuestosStringCreados.get(position).equals("O3" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo())) {
                    igual = "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O4";
                    if (igual.equals(compuesto)) {
                        compuestosStringCreados.set(position, "H2" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O4");
                    }
                } else if (compuestosStringCreados.get(position).equals("O" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "2")) {
                    igual = "H" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O";
                    if (igual.equals(compuesto)) {
                        compuestosStringCreados.set(position, "H" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O");
                    }
                } else if (compuestosStringCreados.get(position).equals("O3" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "2")) {
                    igual = "H" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O2";
                    if (igual.equals(compuesto)) {
                        compuestosStringCreados.set(position, "H" + allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo() + "O2");
                    }
                }

            }
            position++;
        }
        return compuestosStringCreados;
    }

   boolean  compuestovalido(String compuesto,ArrayList<Integer> idsCompuestosCreados, List<ElementoEntity> allElementos, ArrayList<String> compuestosStringCreados) {
        int position = 0;
        String igual;
        boolean valido=true;
        for (int i = 0; i < idsCompuestosCreados.size(); i = i + 3) {
            if (idsCompuestosCreados.get(i)==17 & idsCompuestosCreados.get(i + 1) >9 & idsCompuestosCreados.get(i + 2) == -1) {
                if (compuestosStringCreados.get(position).equals("O" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo())) {
                    igual = "H2" + allElementos.get(idsCompuestosCreados.get(i + 1)).getSimbolo() + "O2";
                    if (igual.equals(compuesto)) {
                        valido = false;
                    }
                }
            }
            if(compuestosStringCreados.get(position).equals("O2"+allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo())){
                    igual="H2"+ allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo()+"O3";
                    if (igual.equals(compuesto)) {
                        valido=false;
                    }
                }
                if(compuestosStringCreados.get(position).equals("O3"+allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo())){
                    igual="H2"+ allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo()+"O4";
                    if (igual.equals(compuesto)) {
                        valido=false;
                    }
                }
               if(compuestosStringCreados.get(position).equals("O"+allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo()+"2")){
                    igual="H"+ allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo()+"O";
                    if (igual.equals(compuesto)) {
                        valido=false;
                    }
                }
                 if(compuestosStringCreados.get(position).equals("O3"+allElementos.get(idsCompuestosCreados.get(i)).getSimbolo()+"2")){
                    igual="H"+ allElementos.get(idsCompuestosCreados.get(i+1)).getSimbolo()+"O2";
                    if (igual.equals(compuesto)) {
                        valido=false;
                    }
                }
            position++;

            }
       return valido;
        }






    }



