package com.cosmoscode.restaurantlisting.service;

import com.cosmoscode.restaurantlisting.dto.RestaurantDTO;
import com.cosmoscode.restaurantlisting.entity.Restaurant;
import com.cosmoscode.restaurantlisting.mapper.RestaurantMapper;
import com.cosmoscode.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();

        //map it to list of DTOs
        List<RestaurantDTO> restaurantDTOList = restaurants.stream()

                .map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
                .collect(Collectors.toList());
        return restaurantDTOList;
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        //convert restaurantDTO to Restaurant
        Restaurant saveRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        //convert Restaurant to RestaurantDTO
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(saveRestaurant);
    }
}
