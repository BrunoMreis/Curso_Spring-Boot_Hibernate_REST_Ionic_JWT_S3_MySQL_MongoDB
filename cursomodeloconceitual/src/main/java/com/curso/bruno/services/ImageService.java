package com.curso.bruno.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.curso.bruno.services.exeption.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente JPG OU PNG");
		}

		try {
			BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				image = pngTojpg(image);
			}

			return image;

		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}

	}

	public BufferedImage pngTojpg(BufferedImage image) {
		BufferedImage newBufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		newBufferedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
		return newBufferedImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro Ao ler arquivo");
		}
	}

	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(
				sourceImg,
				(sourceImg.getWidth() / 2) - (min / 2),
				(sourceImg.getHeight() / 2) - (min / 2),
				min,
				min);
	}

	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);

	}

}
