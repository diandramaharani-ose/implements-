import java.util.Scanner;

public class ArenaPertarungan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Musuh[] gelombangMonster = new Musuh[4];
        gelombangMonster[0] = new Slime();
        gelombangMonster[1] = new Naga();
        gelombangMonster[2] = new Slime();
        gelombangMonster[3] = new Zombie();

        System.out.println("=====================================");
        System.out.println(" ARENA RPG: GELOMBANG MONSTER ");
        System.out.println("=====================================\n");
        System.out.println("AWAS! Sekelompok monster menghadang Anda!");

        boolean isBermain = true;

        while (isBermain) {
            System.out.println("\n--- STATUS MONSTER ---");

            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + " (HP: " + gelombangMonster[i].healthPoint + ")");
                } else {
                    System.out.println((i + 1) + ". " + gelombangMonster[i].namaMusuh + " [DEAD]");
                }
            }
            System.out.println("5. KABOOORRR");
            System.out.print("\nPilih target monster yang ingin diserang (1/2/3/4) atau 5 untuk kabur: ");
            int pilihanTarget = input.nextInt();

            if (pilihanTarget == 5) {
                System.out.println("Cupu bet lu kabur terbirit-birit");
                isBermain = false;
                continue;
            }

            if (pilihanTarget < 1 || pilihanTarget > 5) {
                System.out.println("Pilihan tidak valid! Elu membuang giliran lu.");

            } else {
                System.out.println("Masukkan kekuatan serangan Anda (10 - 100): ");
                int power = input.nextInt();

                System.out.println("\n>>> HASIL SERANGAN ANDA <<<");
                int indeksMonster = pilihanTarget - 1;

                gelombangMonster[indeksMonster].terimaDamage(power);
                if (gelombangMonster[indeksMonster].healthPoint <= 0) {
                    System.out.println(gelombangMonster[indeksMonster].namaMusuh + " dah MATI!");
                    if (gelombangMonster[indeksMonster] instanceof DropItem) {
                        DropItem monsterLoot = (DropItem) gelombangMonster[indeksMonster];
                        monsterLoot.dropItem();
                    }
                }
            }
            System.out.println("\n<<< GILIRAN MONSTER MEMBALAS >>>");
            for (int i = 0; i < gelombangMonster.length; i++) {
                if (gelombangMonster[i].healthPoint > 0) {
                    Musuh monsterAktif = gelombangMonster[i];
                    monsterAktif.suaraKhas();

                    if (monsterAktif instanceof BisaTerbang) {
                        System.out.println("[PERINGATAN! SERANGAN UDARA TERDETEKSI]");
                        BisaTerbang monsterTerbang = (BisaTerbang) monsterAktif;
                        monsterTerbang.lepasLandas();
                        monsterTerbang.seranganUdara();
                    } else {
                        monsterAktif.serangPemain();
                    }
                }
            }
        }
        boolean semuaMati = true;
        for (int i = 0; i < gelombangMonster.length; i++) {
            if (gelombangMonster[i].healthPoint > 0) {
                semuaMati = false;
                break;
            }
        }

        if (semuaMati) {
            System.out.println("\nya ya congrats, lu menang.");
            isBermain = false;
        }
        input.close();
        System.out.println("Game Selesai.");
    }
}