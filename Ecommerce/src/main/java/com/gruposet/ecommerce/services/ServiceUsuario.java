package com.gruposet.ecommerce.services;

import com.gruposet.ecommerce.models.Usuario;
import com.gruposet.ecommerce.daos.DaoUsuario;
import java.util.InputMismatchException;

public abstract class ServiceUsuario {

    public static boolean validaUsuario(Usuario usuario) {
        return validaNome(usuario.getNome())
                || validaApelido(usuario.getApelido()) 
                || validaCpf(usuario.getCpf())
                || validaNascimento(usuario.getData_nasc())
                || validaTelefone(usuario.getTelefone())
                || validaEmail(usuario.getEmail())
                || validaSenha(usuario.getSenha())
                || validaCpf(usuario.getCpf());
    }

    private static boolean validaNome(String nome) {
        return "".equals(nome);
    }

    private static boolean validaApelido(String apelido) {
        return "".equals(apelido);
    }

    private static boolean validaNascimento(String nascimento) {
        return "".equals(nascimento);
    }

    private static boolean validaTelefone(String telefone) {
        return "".equals(telefone);
    }

    private static boolean validaEmail(String email) {
        return "".equals(email) || !email.contains("@");
    }

    private static boolean validaSenha(String senha) {
        return "".equals(senha);
    }
    
    private static boolean validaCpf(String cpf){
        DaoUsuario dao = new DaoUsuario();
        return dao.cpfDuplicado(cpf) && !isCpf(cpf);
    }

    private static boolean isCpf(String cpf) {
        String parte1 = cpf.substring(0, 3);
        String parte2 = cpf.substring(4, 7);
        String parte3 = cpf.substring(8, 11);
        String parte4 = cpf.substring(12, 14);
        String CPF = (parte1) + (parte2) + (parte3) + (parte4);

        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (true);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (false);
            } else {
                return (true);
            }
        } catch (InputMismatchException erro) {
            return (true);
        }
    }
}
