package com.cg.aps.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.aps.entities.DeliveryEntity;
import com.cg.aps.exception.DeliveryIdNotFoundException;
import com.cg.aps.service.DeliveryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/deliveries")
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	Logger logger=LoggerFactory.getLogger(DeliveryController.class);
	
	// ***get all the value by findall***
	@GetMapping
	public ResponseEntity<List<DeliveryEntity>> getDeliveryEntity() {
		logger.info("searching.....");

		List<DeliveryEntity> deliveryList = deliveryService.getDeliveryEntity();
		// Creating an error response.
		ResponseEntity<List<DeliveryEntity>> response = new ResponseEntity<>(deliveryList, HttpStatus.NOT_FOUND);

		if (!deliveryList.isEmpty()) {
			logger.info("searched values found");
			response = new ResponseEntity<>(deliveryList, HttpStatus.OK);
		}

		return response;
	}
	
	//***get delivery details findbyId***
	@GetMapping(value = "/id/{deliveryId}")
	public ResponseEntity<Object> getDelivery(@PathVariable("deliveryId") int deliveryId) {
		logger.info("searching by deliveryId.....");

		DeliveryEntity delivery = deliveryService.getDeliveryById(deliveryId);

		if (delivery == null) {
			logger.error("delivery by ownername is not found");
			throw new DeliveryIdNotFoundException(deliveryId + " Not Found");

		}

		logger.info("delivery by ownername found");
		return new ResponseEntity<>(delivery, HttpStatus.OK);
	}
	
	//***To post and get using deliveryentity ***
			@GetMapping("/delivery")
			 public ResponseEntity<List<DeliveryEntity>> getDeliveryByDelivery(DeliveryEntity sendDelivery ) {
				
				logger.info("delivery is searching....");
				List<DeliveryEntity> deliveryList = deliveryService.getDeliveryByDelivery(sendDelivery);
				// Creating an error response.
				ResponseEntity<List<DeliveryEntity>> response = new ResponseEntity<>(deliveryList, HttpStatus.NOT_FOUND);
				if (!deliveryList.isEmpty()) {
					logger.info("delivery found");
					response = new ResponseEntity<>(deliveryList, HttpStatus.OK);
				}

				return response;
			}
			
	//***getting the value by giving ownerName***
			@GetMapping(value = "/name/{ownerName}")
			public ResponseEntity<Object> getDeliveryOwner(@PathVariable("ownerName") String ownerName) {

				logger.info("delivery is searching by ownername....");
				DeliveryEntity sendDelivery = deliveryService.getDeliveryOwner(ownerName);

				if (sendDelivery == null) {
					logger.error("delivery is not found by ownername");
					throw new DeliveryIdNotFoundException(ownerName + " Not Found");

				}

				logger.info("delivery is found by ownername");
				return new ResponseEntity<>(sendDelivery, HttpStatus.OK);
			}
			
	//***inserting the values***
	@PostMapping
	public ResponseEntity<Object> addDelivery(@RequestBody DeliveryEntity delivery) {
		logger.info("adding new delivery.....");

		DeliveryEntity newDelivery = deliveryService.insertDeliveryEntity(delivery);

		if (newDelivery == null)
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
		// response is set to inserted delivery id in response header section.
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDelivery.getDeliveryId()).toUri();
		logger.info("delivery is added");
		return ResponseEntity.created(location).build();
	}
	
	//***updating the values***
	@PutMapping
	public ResponseEntity<Object> updateDelivery(@RequestBody DeliveryEntity delivery) {
		logger.info("delivery is updating.....");

		// If delivery is updated it returns updates delivery object else null
		DeliveryEntity deliveryUpdate =deliveryService.updateDeliveryEntity(delivery);
		// response is set to error if delivery is null.
		if (deliveryUpdate == null) {
			
			logger.error("delivery is not updated");
			throw new DeliveryIdNotFoundException(delivery + " Not Found");
		} else {
			// response is set to updated delivery id in response header section.
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(delivery.getDeliveryId()).toUri();
			logger.info("delivery is updated");
			return ResponseEntity.created(location).build();
		}
	}
	
	//***deleting the values***
	@DeleteMapping(value = "/{deliveryId}")
	public ResponseEntity<Object> deleteDelivery(@RequestBody @PathVariable("deliveryId") int id) {
		logger.info("delivery is deleting....");
		DeliveryEntity deliveryPresent = deliveryService.deleteDeliveryEntity(id);
		
		if (deliveryPresent == null) {
			logger.error("delivery is not found to delete");
			throw new DeliveryIdNotFoundException(id + " Not Found");
		}
		logger.info("delivery is deleted");
		return ResponseEntity.status(HttpStatus.OK).body("DeliveryEntity " + id + " deleted");
	}
	
	//***pagination***
	@GetMapping("/page")
    public ResponseEntity<List<DeliveryEntity>> getDeliveryPage(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "deliveryId") String sortBy) 
    {
		logger.info("pagination starting....");
		List<DeliveryEntity> list = deliveryService.getDeliveryPage(pageNo, pageSize, sortBy);
        if (list == null) {
        	logger.error("page not found");
			throw new DeliveryIdNotFoundException(list + " Not Found");

		}

        logger.info("showing list of values in page");
        return new ResponseEntity<List<DeliveryEntity>> (list, new HttpHeaders(), HttpStatus.OK); 
    }
	


}