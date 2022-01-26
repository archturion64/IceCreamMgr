package com.github.archturion64.CodingChallengeIceCream.control;

import com.github.archturion64.CodingChallengeIceCream.configuration.exceptions.IceCreamAlreadyExistsException;
import com.github.archturion64.CodingChallengeIceCream.entity.IceCreamFlavorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IceCreamFlavorService {

    private final IceCreamFlavorRepository iceCreamFlavorRepository;

    public void add(final IceCreamFlavor flavor) {
        if(iceCreamFlavorRepository.findByName(flavor.getName()).isPresent()) {
            throw new IceCreamAlreadyExistsException();
        }
        iceCreamFlavorRepository.save(IceCreamFlavorEntityMapper.map(flavor));
    }

    public List<IceCreamFlavor> getAll() {
        return iceCreamFlavorRepository.findAll()
                .stream()
                .map(IceCreamFlavorEntityMapper::map)
                .collect(Collectors.toList());
    }
}
