package com.formacion.bosonit.block11uploaddownloadfiles;

import com.formacion.bosonit.block11uploaddownloadfiles.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			if (args.length > 0) {
				storageService.setRootLocation(Path.of(args[0]));
			} else {
				storageService.init();
			}
		};
	}
}
