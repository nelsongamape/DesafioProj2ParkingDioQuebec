package br.com.rodrigocbarj.cloudparking.controller;

import br.com.rodrigocbarj.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.rodrigocbarj.cloudparking.controller.dto.ParkingDTO;
import br.com.rodrigocbarj.cloudparking.controller.mapper.ParkingMapper;
import br.com.rodrigocbarj.cloudparking.model.Parking;
import br.com.rodrigocbarj.cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService,
                             ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all Parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        return ResponseEntity.ok(parkingMapper.toParkingDTOList(parkingList));
    }

    @GetMapping("/{id}")
    @ApiOperation("Find Parking by ID")
    public ResponseEntity<ParkingDTO> findById(@PathVariable Long id) {
        Parking parking = parkingService.findById(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

    @PostMapping
    @ApiOperation("Create new Parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO createDTO) {
        Parking newParking = parkingService.create(parkingMapper.toParkingByCreate(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingMapper.toParkingDTO(newParking));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Parking")
    public ResponseEntity<ParkingDTO> update(@PathVariable Long id ,
                                             @RequestBody ParkingCreateDTO dto) {
        Parking updatedParking = parkingService.update(id ,parkingMapper.toParkingByCreate(dto));
        return ResponseEntity.status(HttpStatus.OK)
                .body(parkingMapper.toParkingDTO(updatedParking));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete by ID")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/{id}")
    @ApiOperation("Exit")
    public ResponseEntity<ParkingDTO> exit(@PathVariable Long id) {
        Parking parking = parkingService.exit(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }
}
