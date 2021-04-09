package UtilityFunctions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CodeGenerators {
    // Generez un numar aleator de 9 cifre si apoi, cu
    // ajutorul algoritmului Luhn generez a 10-a cifra
    // cunoscuta drept cifra de verificare (check digit)
    // care indica daca un numar de cont este valid sau
    // nu. In mod normal ar fi trebuit sa generez direct
    // un numar de 10 cifre si apoi cu algoritmul Luhn
    // sa verific daca este un numar de cont valid,
    // dar asta presupunea generarea unui numar posibil
    // destul de mare pana la gasirea unui cod corect
    public static Long generateAccountNumber(Set<Long> accountNumbers) {
        boolean isUnique = false;
        long maxKey = 0L;
        while(isUnique != true) {
            boolean next = false;
            maxKey = ThreadLocalRandom.current().nextInt(100000000, 999999999 + 1);

            Iterator<Long> it = accountNumbers.iterator();
            while(it.hasNext()) {
                long maxKeylong = maxKey;
                if(it.next() == maxKeylong) {
                    next = true;
                    break;
                }
            }

            if(next == false) {
                isUnique = true;
                break;
            }
        }
        long luhnKeyAux = 0L;
        long copyKey = maxKey;
        int sum = 0;
        boolean evenPos = true;
        while(copyKey != 0) {
            long digit = copyKey % 10;
            if(evenPos == true) {
                digit = digit * 2;
                if(digit > 9) {
                    digit -= 9;
                }
            }
            luhnKeyAux = luhnKeyAux * 10 + digit;
            sum += digit;
            copyKey /= 10;
            evenPos = !evenPos;
        }

        long luhnKey = 0L;
        while(luhnKeyAux != 0) {
            luhnKey = luhnKey * 10 + luhnKeyAux % 10;
            luhnKeyAux = luhnKeyAux / 10;
        }
        luhnKey = luhnKey * 10 + (sum * 9) % 10;
        Long luhnKeyLong = maxKey * 10 + (sum * 9) % 10;
        return luhnKeyLong;
    }

    public static Long generateCardNumber(Set<Long> accountNumbers) {
        boolean isUnique = false;
        long maxKey = 0L;
        while(isUnique != true) {
            boolean next = false;
            maxKey = ThreadLocalRandom.current().nextLong(10000000000000L, 99999999999999L + 1);

            Iterator<Long> it = accountNumbers.iterator();
            while(it.hasNext()) {
                long maxKeylong = maxKey;
                if(it.next() == maxKeylong) {
                    next = true;
                    break;
                }
            }

            if(next == false) {
                isUnique = true;
                break;
            }
        }
        long reversedKey = 0L;
        long copyKey = maxKey;
        int sum = 0;
        boolean evenPos = true;
        while(copyKey != 0) {
            reversedKey = reversedKey * 10 + copyKey % 10;
            copyKey /= 10;
        }
        while(reversedKey != 0) {
            long digit = reversedKey % 10;
            if(evenPos == true) {
                digit = digit * 2;
                if(digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            reversedKey /= 10;
            evenPos = !evenPos;
        }

        long luhnKey = 0L;
        luhnKey = 4 * (10 ^ 16) +  maxKey * 10 + sum % 10;
        return luhnKey;
    }
}
