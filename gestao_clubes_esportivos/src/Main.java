import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Autor: Mateus Henrique Bosquetti
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("==== SISTEMA DE GESTÃO DE CLUBES ESPORTIVOS ====\n" +
                    "1. Gerenciar Clubes\n" +
                    "2. Gerenciar Treinadores\n" +
                    "3. Gerenciar Jogadores\n" +
                    "4. Gerenciar Ligas\n" +
                    "5. Sair\n" +
                    "Escolha uma opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    menuClube();
                    break;
                case 2:
                    menuTreinador();
                    break;
                case 3:
                    menuJogador();
                    break;
                case 4:
                    menuLiga();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void menuLiga() {
        int escolha = 0;

        while (escolha != 6) {
            System.out.print("\n==== GERENCIAR LIGAS ====\n" +
                    "1. Cadastrar nova liga\n" +
                    "2. Listar todas as ligas\n" +
                    "3. Atualizar dados de uma liga\n" +
                    "4. Excluir uma liga\n" +
                    "5. Listar clubes em uma liga\n" +
                    "6. Voltar ao menu principal\n" +
                    "Escolha uma opção: ");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Ano de Fundação: ");
                    int anoFundacao = sc.nextInt();
                    sc.nextLine();

                    try {
                        LigaCRUD.postLiga(new Liga(nome, anoFundacao));
                        System.out.println("Liga Cadastrada com Sucesso");
                    } catch (Exception e) {
                        System.out.println("Erro na validação dos Dados, tente novamente");
                    }
                    break;
                case 2:
                    System.out.println(LigaCRUD.getLigas());
                    break;
                case 3:
                    System.out.print("ID da liga que deseja editar: ");
                    int idLiga = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo Nome: ");
                    String putNome = sc.nextLine();

                    System.out.print("Novo Ano de Fundação: ");
                    int putAnoFundacao = sc.nextInt();
                    sc.nextLine();

                    try {
                        LigaCRUD.putLiga(new Liga(putNome, putAnoFundacao, idLiga));
                        System.out.println("Liga Editada com Sucesso");
                    } catch (Exception e) {
                        System.out.println("Liga não encontrado. Tente novamente.");
                    }
                    break;
                case 4:
                    System.out.print("ID da liga que deseja excluir: ");
                    int deleteLiga = sc.nextInt();
                    sc.nextLine();

                    try {
                        LigaCRUD.deleteLiga(deleteLiga);
                        System.out.println("Liga Excluída com Sucesso");
                    } catch (Exception e) {
                        System.out.println("Liga não encontrado. Tente novamente.");
                    }
                    break;
                case 5:
                    System.out.print("ID da liga que deseja buscar: ");
                    int getIdLiga = sc.nextInt();

                    ClubeLigaCRUD.getClubeByLiga(getIdLiga);

                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void menuJogador() {
        int escolha = 0;

        while (escolha != 6) {
            System.out.print("\n==== GERENCIAR JOGADORES ====\n" +
                    "1. Cadastrar novo jogador\n" +
                    "2. Listar todos os jogadores\n" +
                    "3. Listar jogadores de um clube\n" +
                    "4. Atualizar dados de um jogador\n" +
                    "5. Excluir um jogador\n" +
                    "6. Voltar ao menu principal\n" +
                    "Escolha uma opção: ");
            escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Posicao: ");
                    String posicao = sc.nextLine();

                    System.out.print("ID do Clube: ");
                    int idClube = sc.nextInt();
                    sc.nextLine();

                    try {
                        JogadorCRUD.postJogador(new Jogador(ClubeCRUD.getClubeById(idClube), idade, nome, posicao));
                        System.out.println("Jogador cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Clube não encontrado. Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println(JogadorCRUD.getJogador());
                    break;
                case 3:
                    System.out.print("ID do Clube: ");
                    int getIdClube = sc.nextInt();
                    sc.nextLine();
                    System.out.println(JogadorCRUD.getJogadorByClube(getIdClube));
                    break;
                case 4:
                    System.out.print("ID do Jogador que deseja editar: ");
                    int idJogador = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo Nome: ");
                    String putNome = sc.nextLine();

                    System.out.print("Nova Idade: ");
                    int putIdade = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nova Posicao: ");
                    String putPosicao = sc.nextLine();

                    System.out.print("Novo ID do Clube: ");
                    int putIdClube = sc.nextInt();
                    sc.nextLine();

                    try {
                        JogadorCRUD.putJogador(new Jogador(ClubeCRUD.getClubeById(putIdClube), putIdade, putNome, idJogador, putPosicao));
                        System.out.println("Jogador editado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Clube ou Jogador não encontrado. Tente novamente.");
                    }
                    break;
                case 5:
                    System.out.print("ID do jogador que deseja excluir: ");
                    int deleteJogador = sc.nextInt();
                    sc.nextLine();

                    try {
                        JogadorCRUD.deleteJogador(deleteJogador);
                        System.out.println("Jogador excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Jogador não encontrado. Tente novamente.");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void menuTreinador() {
        int escolha = 0;

        while (escolha != 5) {
            System.out.print("\n==== GERENCIAR TREINADORES ====\n" +
                    "1. Cadastrar novo treinador\n" +
                    "2. Listar todos os treinadores\n" +
                    "3. Atualizar dados de um treinador\n" +
                    "4. Excluir um treinador\n" +
                    "5. Voltar ao menu principal\n" +
                    "Escolha uma opção: ");
            escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Experiência: ");
                    int experiencia = sc.nextInt();
                    sc.nextLine();

                    TreinadorCRUD.postTreinador(new Treinador(nome, experiencia));
                    break;
                case 2:
                    System.out.println(TreinadorCRUD.getTreinador());
                    break;
                case 3:
                    System.out.print("ID do treinador que deseja editar: ");
                    int idTreinador = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome: ");
                    String putNome = sc.nextLine();

                    System.out.print("Experiência: ");
                    int putExperiencia = sc.nextInt();
                    sc.nextLine();

                    try {
                        TreinadorCRUD.putTreinador(new Treinador(putNome, putExperiencia, idTreinador));
                        System.out.println("Treinador editado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Treinador não encontrado. Tente novamente.");
                    }
                    break;
                case 4:
                    System.out.print("ID do treinador que deseja excluir: ");
                    int deleteTreinador = sc.nextInt();
                    sc.nextLine();

                    try {
                        TreinadorCRUD.deleteTreinador(deleteTreinador);
                        System.out.println("Treinador excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Treinador não encontrado. Tente novamente.");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void menuClube() {
        int escolha = 0;

        while (escolha != 6) {
            System.out.print("\n==== GERENCIAR CLUBES ====\n" +
                    "1. Cadastrar novo clube\n" +
                    "2. Listar todos os clubes\n" +
                    "3. Atualizar dados de um clube\n" +
                    "4. Excluir um clube\n" +
                    "5. Gerenciar ligas do clube\n" +
                    "6. Voltar ao menu principal\n" +
                    "Escolha uma opção: ");
            escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Data Fundação: ");
                    String dataFundacao = sc.nextLine();

                    System.out.print("ID do Treinador: ");
                    int idTreinador = sc.nextInt();
                    sc.nextLine();

                    try {
                        ClubeCRUD.postClube(new Clube(nome, TreinadorCRUD.getTreinadorById(idTreinador), dataFundacao));
                        System.out.println("Clube cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Treinador não encontrado. Tente novamente.");
                    }
                    break;
                case 2:
                    System.out.println(ClubeCRUD.getClube());
                    break;
                case 3:
                    System.out.print("ID do clube que deseja editar: ");
                    int idClube = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome: ");
                    String putNome = sc.nextLine();

                    System.out.print("Data Fundação: ");
                    String putDataFundacao = sc.nextLine();

                    System.out.print("ID do Treinador: ");
                    int putIdTreinador = sc.nextInt();
                    sc.nextLine();

                    try {
                        ClubeCRUD.putClube(new Clube(idClube, putNome, TreinadorCRUD.getTreinadorById(putIdTreinador), putDataFundacao));
                        System.out.println("Clube editado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Clube ou Treinador não encontrado. Tente novamente.");
                    }
                    break;
                case 4:
                    System.out.print("ID do clube que deseja excluir: ");
                    int deleteClube = sc.nextInt();
                    sc.nextLine();

                    try {
                        ClubeCRUD.deleteClube(deleteClube);
                        System.out.println("Clube excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Clube não encontrado. Tente novamente.");
                    }
                    break;
                case 5:
                    menuLigaClube();
                    break;
                case 6:
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void menuLigaClube() {
        int escolha = 0;

        while (escolha != 4) {
            System.out.print("\n==== GERENCIAR LIGAS DO CLUBE ====\n" +
                    "1. Listar ligas associadas ao clube\n" +
                    "2. Adicionar clube a uma liga\n" +
                    "3. Remover clube de uma liga\n" +
                    "4. Voltar ao menu anterior\n" +
                    "Escolha uma opção: ");
            escolha = sc.nextInt();
            sc.nextLine();
            switch (escolha) {
                case 1:
                    System.out.print("ID do Clube que deseja listar: ");
                    int getClubeId = sc.nextInt();
                    sc.nextLine();

                    System.out.println(ClubeLigaCRUD.getLigaByClube(getClubeId));
                    break;
                case 2:
                    System.out.print("ID do Clube: ");
                    int clubeId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ID da Liga: ");
                    int ligaId = sc.nextInt();
                    sc.nextLine();

                    try {
                        ClubeLigaCRUD.postClubeLiga(ClubeCRUD.getClubeById(clubeId), LigaCRUD.getLigaById(ligaId));
                        System.out.println("Relacionamento de Clube e Liga cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Clube ou Liga não encontrados. Tente novamente");
                    }
                    break;
                case 3:
                    System.out.print("ID do Clube que deseja remover: ");
                    int deleteClubeId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ID da Liga que deseja remover o clube " + deleteClubeId + ": ");
                    int deleteLigaId = sc.nextInt();
                    sc.nextLine();

                    try {
                        ClubeLigaCRUD.deleteClubeLiga(deleteClubeId, deleteLigaId);
                        System.out.println("Relacionamento de Clube e Liga excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Clube ou Liga não encontrados. Tente novamente");
                    }
                    break;

                case 4:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}