package controllers.util;

public class Cpf {
    
    public static boolean isValid(String cpf){
        if(cpf.length() != 11){
            return false;
        }

        int[] digitos = new int[11];
        for(int i = 0; i < 11; i++){
            digitos[i] = Integer.parseInt(cpf.substring(i, i+1));
        }

        int soma = 0;
        for(int i = 0; i < 9; i++){
            soma += digitos[i] * (10 - i);
        }

        int resultado = soma % 11;
        if(resultado == 1 || resultado == 0){
            if(digitos[9] != 0){
                return false;
            }
        }else{
            if(digitos[9] != 11 - resultado){
                return false;
            }
        }

        soma = 0;
        for(int i = 0; i < 10; i++){
            soma += digitos[i] * (11 - i);
        }

        resultado = soma % 11;
        if(resultado == 1 || resultado == 0){
            if(digitos[10] != 0){
                return false;
            }
        }else{
            if(digitos[10] != 11 - resultado){
                return false;
            }
        }

        return true;
    }
}
