package com.eray.expenseTrackingAPI.dto.converter;

import com.eray.expenseTrackingAPI.dto.UserDto;
import com.eray.expenseTrackingAPI.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User from){
        return new UserDto(from.getName(), from.getSurname(),
                from.getCountry(),from.getCity(),from.getAddress());
    }

    // for unit testing
    public List<UserDto> convert(List<User> fromList){
        return fromList.stream()
                .map(from -> new UserDto(from.getName(), from.getSurname(),
                        from.getCountry(),from.getCity(),from.getAddress()))
                .collect(Collectors.toList());
    }
}
