package com.github.archturion64.CodingChallengeIceCream;

import com.github.archturion64.CodingChallengeIceCream.control.Category;
import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavor;
import com.github.archturion64.CodingChallengeIceCream.control.IceCreamFlavorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class CodingChallengeIceCreamApplication implements CommandLineRunner {

	private final IceCreamFlavorService iceCreamFlavorService;

	public CodingChallengeIceCreamApplication(IceCreamFlavorService iceCreamFlavorService) {
		this.iceCreamFlavorService = iceCreamFlavorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CodingChallengeIceCreamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		iceCreamFlavorService.add(new IceCreamFlavor("Bourbon Vanille",
				Category.CREAM_BASED,
				new HashSet<>(List.of("Milch", "Kokosfett", "Zucker", "Schlagsahne", "Bourbon-Vanilleextrakt", "Vanilleschoten")),
				"Laktoseintoleranz",
				208,
				3.65));
		iceCreamFlavorService.add(new IceCreamFlavor("Chocolate Chips",
				Category.CREAM_BASED,
				new HashSet<>(List.of("Milch", "Zucker", "Kakao", "Schlagsahne")),
				"Laktoseintoleranz, Schalenfrüchte",
				219,
				3.65)
		);
	}
}
