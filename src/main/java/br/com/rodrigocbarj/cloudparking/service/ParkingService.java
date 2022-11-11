package br.com.rodrigocbarj.cloudparking.service;

import br.com.rodrigocbarj.cloudparking.exception.ParkingNotFoundException;
import br.com.rodrigocbarj.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private static Map<Long, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID();
//        var id1 = getUUID();
        Parking parking = new Parking(id, "PMA-3213", "PE", "POLO", "PRETO");
//        Parking parking1 = new Parking(id1, "AMP-3123", "SP", "GOL", "BRANCO");
        parkingMap.put(id, parking);
//        parkingMap.put(id1, parking1);
    }

    private static Long getUUID() {
        long id = UUID.randomUUID().hashCode();
        while (id < 0) {
            id = UUID.randomUUID().hashCode();
        }
        return id;
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(Long id) {
        Parking parking = parkingMap.get(id);

        if (parking == null) throw new ParkingNotFoundException(id);

        return parking;
    }

    public Parking create(Parking parking) {
        Long id = getUUID();
        parking.setId(id);
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        return parking;
    }

    public Parking update(Long id, Parking parkingChanges) {
        Parking parking = findById(id);
        parking.setLicense(parkingChanges.getLicense());
        parking.setState(parkingChanges.getState());
        parking.setModel(parkingChanges.getModel());
        parking.setColor(parkingChanges.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }

    public void delete(Long id) {
        findById(id); // se não existir esse id, não remove.
        parkingMap.remove(id);
    }

    public Parking exit(Long id) {
        // TODO:
        // pegar o estacionado
        // atualizar data de saida
        // calcular valor
        return null;
    }
}
