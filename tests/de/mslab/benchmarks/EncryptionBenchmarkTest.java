package de.mslab.benchmarks;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.mslab.ciphers.AES128;
import de.mslab.ciphers.AES192;
import de.mslab.ciphers.AES256;
import de.mslab.ciphers.ARIA128;
import de.mslab.ciphers.ARIA192;
import de.mslab.ciphers.ARIA256;
import de.mslab.ciphers.Khazad;
import de.mslab.ciphers.RoundBasedBlockCipher;
import de.mslab.ciphers.SQUARE;
import de.mslab.ciphers.ThreeFish1024;
import de.mslab.ciphers.ThreeFish256;
import de.mslab.ciphers.ThreeFish512;
import de.mslab.ciphers.WhirlpoolCipher;
import de.mslab.core.ByteArray;
import de.mslab.utils.Logger;
import de.mslab.utils.MathUtil;

public class EncryptionBenchmarkTest {
	
	private static final Logger logger = new Logger();
	private static final long numIterations = 100000;
	
	private static AES128 aes128 = new AES128();
	private static AES192 aes192 = new AES192();
	private static AES256 aes256 = new AES256();
	private static ARIA128 aria128 = new ARIA128();
	private static ARIA192 aria192 = new ARIA192();
	private static ARIA256 aria256 = new ARIA256();
	private static Khazad khazad = new Khazad();
	private static SQUARE square = new SQUARE();
	private static ThreeFish256 threeFish256 = new ThreeFish256();
	private static ThreeFish512 threeFish512 = new ThreeFish512();
	private static ThreeFish1024 threeFish1024 = new ThreeFish1024();
	private static WhirlpoolCipher whirlpool = new WhirlpoolCipher();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		aes128 = null;
		aes192 = null;
		aes256 = null;
		aria128 = null;
		aria192 = null;
		aria256 = null;
		khazad = null;
		square = null;
		threeFish256 = null;
		threeFish512 = null;
		threeFish1024 = null;
		whirlpool = null;
	}
	
	@Test
	public void testHammingWeight() {
		long startTime = System.nanoTime();
		long numTests = 100000000;
		
		for (int i = 0; i < numTests; i++) {
			MathUtil.hasHigherHammingWeightThan(i, 32, 4);
		}
		
		long endTime = System.nanoTime();
		logger.info("{0} comparisons of the hamming weight in {1}", numIterations, getSeconds(startTime, endTime));
	}
	
	@Test
	public void runAES128() {
		int[] key = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f }; 
		int[] plaintext = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff };
		int[] ciphertext = { 0x69, 0xc4, 0xe0, 0xd8, 0x6a, 0x7b, 0x04, 0x30, 0xd8, 0xcd, 0xb7, 0x80, 0x70, 0xb4, 0xc5, 0x5a };
		testAll(aes128, key, plaintext, ciphertext);
	}
	
	@Test
	public void runAES192() {
		int[] key = {
			0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c,
			0x76, 0x2e, 0x71, 0x60, 0xf3, 0x8b, 0x4d, 0xa5
		};
		int[] plaintext = { 0x32, 0x43, 0xf6, 0xa8, 0x88, 0x5a, 0x30, 0x8d, 0x31, 0x31, 0x98, 0xa2, 0xe0, 0x37, 0x07, 0x34 };
		int[] ciphertext = { 0xf9, 0xfb, 0x29, 0xae, 0xfc, 0x38, 0x4a, 0x25, 0x03, 0x40, 0xd8, 0x33, 0xb8, 0x7e, 0xbc, 0x00 };
		testAll(aes192, key, plaintext, ciphertext);
	}
	
	@Test
	public void runAES256() {
		int[] key = {
			0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
			0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f
		};
		int[] plaintext = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff };
		int[] ciphertext = { 0x8e, 0xa2, 0xb7, 0xca, 0x51, 0x67, 0x45, 0xbf, 0xea, 0xfc, 0x49, 0x90, 0x4b, 0x49, 0x60, 0x89 };
		testAll(aes256, key, plaintext, ciphertext);
	}
	
	@Test
	public void runARIA128() {
		int[] key = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		int[] plaintext = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff };
		int[] ciphertext = { 0xd7, 0x18, 0xfb, 0xd6, 0xab, 0x64, 0x4c, 0x73, 0x9d, 0xa9, 0x5f, 0x3b, 0xe6, 0x45, 0x17, 0x78 };
		testAll(aria128, key, plaintext, ciphertext);
	}
	
	@Test
	public void runARIA192() {
		int[] key = { 
			0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 
			0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17
		};
		int[] plaintext = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff };
		int[] ciphertext = { 0x26, 0x44, 0x9c, 0x18, 0x05, 0xdb, 0xe7, 0xaa, 0x25, 0xa4, 0x68, 0xce, 0x26, 0x3a, 0x9e, 0x79 };
		testAll(aria192, key, plaintext, ciphertext);
	}
	
	@Test
	public void runARIA256() {
		int[] key = {
			0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 
			0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f
		};
		int[] plaintext = { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff };
		int[] ciphertext = { 0xf9, 0x2b, 0xd7, 0xc7, 0x9f, 0xb7, 0x2e, 0x2f, 0x2b, 0x8f, 0x80, 0xc1, 0x97, 0x2d, 0x24, 0xfc };
		testAll(aria256, key, plaintext, ciphertext);
	}
	
	@Test
	public final void runKhazad() {
		int[] key = { 0x80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] plaintext = { 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] ciphertext = { 0x49, 0xA4, 0xCE, 0x32, 0xAC, 0x19, 0x0E, 0x3F };
		testAll(khazad, key, plaintext, ciphertext);
	}
	
	@Test
	public final void runSQUARE() {
		int[] key = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		int[] plaintext = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f };
		int[] ciphertext = { 0x31, 0x33, 0xdd, 0x27, 0xf3, 0xcc, 0x0b, 0x67, 0x27, 0x9c, 0x41, 0x48, 0x01, 0x16, 0x66, 0x77 };
		testAll(square, key, plaintext, ciphertext);
	}

	@Test
	public final void runThreeFish256() {
		int[] tweak = { // 07060504.03020100  0F0E0D0C.0B0A0908 
			0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00, 0x0F, 0x0E, 0x0D, 0x0C, 0x0B, 0x0A, 0x09, 0x08 
		};
		int[] key = { // 17161514.13121110  1F1E1D1C.1B1A1918  27262524.23222120  2F2E2D2C.2B2A2928 
			0x17, 0x16, 0x15, 0x14, 0x13, 0x12, 0x11, 0x10, 0x1F, 0x1E, 0x1D, 0x1C, 0x1B, 0x1A, 0x19, 0x18,   
			0x27, 0x26, 0x25, 0x24, 0x23, 0x22, 0x21, 0x20, 0x2F, 0x2E, 0x2D, 0x2C, 0x2B, 0x2A, 0x29, 0x28
		};
		int[] plaintext = { // F8F9FAFB.FCFDFEFF  F0F1F2F3.F4F5F6F7  E8E9EAEB.ECEDEEEF  E0E1E2E3.E4E5E6E7 
			0xF8, 0xF9, 0xFA, 0xFB, 0xFC, 0xFD, 0xFE, 0xFF, 0xF0, 0xF1, 0xF2, 0xF3, 0xF4, 0xF5, 0xF6, 0xF7, 
			0xE8, 0xE9, 0xEA, 0xEB, 0xEC, 0xED, 0xEE, 0xEF, 0xE0, 0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xE6, 0xE7
		};
		int[] ciphertext = {
			0xDF, 0x8F, 0xEA, 0x0E, 0xFF, 0x91, 0xD0, 0xE0, 0xD5, 0x0A, 0xD8, 0x2E, 0xE6, 0x92, 0x81, 0xC9, 
			0x76, 0xF4, 0x8D, 0x58, 0x08, 0x5D, 0x86, 0x9D, 0xDF, 0x97, 0x5E, 0x95, 0xB5, 0x56, 0x70, 0x65
		};
		
		threeFish256.setTweak(new ByteArray(tweak));
		testAll(threeFish256, key, plaintext, ciphertext);
	}

	@Test
	public final void runThreeFish512() {
		int[] tweak = { // 07060504.03020100  0F0E0D0C.0B0A0908
			0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00, 0x0F, 0x0E, 0x0D, 0x0C, 0x0B, 0x0A, 0x09, 0x08 
		};
		int[] key = { 
			// 17161514.13121110  1F1E1D1C.1B1A1918  27262524.23222120  2F2E2D2C.2B2A2928 
		    // 37363534.33323130  3F3E3D3C.3B3A3938  47464544.43424140  4F4E4D4C.4B4A4948 
			0x17, 0x16, 0x15, 0x14, 0x13, 0x12, 0x11, 0x10, 0x1F, 0x1E, 0x1D, 0x1C, 0x1B, 0x1A, 0x19, 0x18,   
			0x27, 0x26, 0x25, 0x24, 0x23, 0x22, 0x21, 0x20, 0x2F, 0x2E, 0x2D, 0x2C, 0x2B, 0x2A, 0x29, 0x28, 
			0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x30, 0x3F, 0x3E, 0x3D, 0x3C, 0x3B, 0x3A, 0x39, 0x38,   
			0x47, 0x46, 0x45, 0x44, 0x43, 0x42, 0x41, 0x40, 0x4F, 0x4E, 0x4D, 0x4C, 0x4B, 0x4A, 0x49, 0x48
		};
		int[] plaintext = { 
			// F8F9FAFB.FCFDFEFF  F0F1F2F3.F4F5F6F7  E8E9EAEB.ECEDEEEF  E0E1E2E3.E4E5E6E7 
			// D8D9DADB.DCDDDEDF  D0D1D2D3.D4D5D6D7  C8C9CACB.CCCDCECF  C0C1C2C3.C4C5C6C7 
			0xF8, 0xF9, 0xFA, 0xFB, 0xFC, 0xFD, 0xFE, 0xFF, 0xF0, 0xF1, 0xF2, 0xF3, 0xF4, 0xF5, 0xF6, 0xF7, 
			0xE8, 0xE9, 0xEA, 0xEB, 0xEC, 0xED, 0xEE, 0xEF, 0xE0, 0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xE6, 0xE7, 
			0xD8, 0xD9, 0xDA, 0xDB, 0xDC, 0xDD, 0xDE, 0xDF, 0xD0, 0xD1, 0xD2, 0xD3, 0xD4, 0xD5, 0xD6, 0xD7, 
			0xC8, 0xC9, 0xCA, 0xCB, 0xCC, 0xCD, 0xCE, 0xCF, 0xC0, 0xC1, 0xC2, 0xC3, 0xC4, 0xC5, 0xC6, 0xC7
		};
		int[] ciphertext = {
			0x2C, 0x5A, 0xD4, 0x26, 0x96, 0x43, 0x04, 0xE3, 0x9A, 0x24, 0x36, 0xD6, 0xD8, 0xCA, 0x01, 0xB4, 
			0xDD, 0x45, 0x6D, 0xB0, 0x0E, 0x33, 0x38, 0x63, 0x79, 0x47, 0x25, 0x97, 0x0E, 0xB9, 0x36, 0x8B, 
			0x04, 0x35, 0x46, 0x99, 0x8D, 0x0A, 0x2A, 0x27, 0x25, 0xA7, 0xC9, 0x18, 0xEA, 0x20, 0x44, 0x78, 
			0x34, 0x62, 0x01, 0xA1, 0xFE, 0xDF, 0x11, 0xAF, 0x3D, 0xAF, 0x1C, 0x5C, 0x3D, 0x67, 0x27, 0x89
		};
		
		threeFish512.setTweak(new ByteArray(tweak));
		testAll(threeFish512, key, plaintext, ciphertext);
	}
	
	@Test
	public final void runThreeFish1024() {
		int[] tweak = { // 07060504.03020100  0F0E0D0C.0B0A0908
			0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00, 0x0F, 0x0E, 0x0D, 0x0C, 0x0B, 0x0A, 0x09, 0x08 
		};
		int[] key = { 
			0x17, 0x16, 0x15, 0x14, 0x13, 0x12, 0x11, 0x10, 0x1F, 0x1E, 0x1D, 0x1C, 0x1B, 0x1A, 0x19, 0x18,   
			0x27, 0x26, 0x25, 0x24, 0x23, 0x22, 0x21, 0x20, 0x2F, 0x2E, 0x2D, 0x2C, 0x2B, 0x2A, 0x29, 0x28, 
			0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x30, 0x3F, 0x3E, 0x3D, 0x3C, 0x3B, 0x3A, 0x39, 0x38,   
			0x47, 0x46, 0x45, 0x44, 0x43, 0x42, 0x41, 0x40, 0x4F, 0x4E, 0x4D, 0x4C, 0x4B, 0x4A, 0x49, 0x48, 
			0x57, 0x56, 0x55, 0x54, 0x53, 0x52, 0x51, 0x50, 0x5F, 0x5E, 0x5D, 0x5C, 0x5B, 0x5A, 0x59, 0x58,   
			0x67, 0x66, 0x65, 0x64, 0x63, 0x62, 0x61, 0x60, 0x6F, 0x6E, 0x6D, 0x6C, 0x6B, 0x6A, 0x69, 0x68, 
			0x77, 0x76, 0x75, 0x74, 0x73, 0x72, 0x71, 0x70, 0x7F, 0x7E, 0x7D, 0x7C, 0x7B, 0x7A, 0x79, 0x78,   
			0x87, 0x86, 0x85, 0x84, 0x83, 0x82, 0x81, 0x80, 0x8F, 0x8E, 0x8D, 0x8C, 0x8B, 0x8A, 0x89, 0x88
		};
		int[] plaintext = { 
			0xF8, 0xF9, 0xFA, 0xFB, 0xFC, 0xFD, 0xFE, 0xFF, 0xF0, 0xF1, 0xF2, 0xF3, 0xF4, 0xF5, 0xF6, 0xF7, 
			0xE8, 0xE9, 0xEA, 0xEB, 0xEC, 0xED, 0xEE, 0xEF, 0xE0, 0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xE6, 0xE7, 
			0xD8, 0xD9, 0xDA, 0xDB, 0xDC, 0xDD, 0xDE, 0xDF, 0xD0, 0xD1, 0xD2, 0xD3, 0xD4, 0xD5, 0xD6, 0xD7, 
			0xC8, 0xC9, 0xCA, 0xCB, 0xCC, 0xCD, 0xCE, 0xCF, 0xC0, 0xC1, 0xC2, 0xC3, 0xC4, 0xC5, 0xC6, 0xC7, 
			0xB8, 0xB9, 0xBA, 0xBB, 0xBC, 0xBD, 0xBE, 0xBF, 0xB0, 0xB1, 0xB2, 0xB3, 0xB4, 0xB5, 0xB6, 0xB7, 
			0xA8, 0xA9, 0xAA, 0xAB, 0xAC, 0xAD, 0xAE, 0xAF, 0xA0, 0xA1, 0xA2, 0xA3, 0xA4, 0xA5, 0xA6, 0xA7, 
			0x98, 0x99, 0x9A, 0x9B, 0x9C, 0x9D, 0x9E, 0x9F, 0x90, 0x91, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 
			0x88, 0x89, 0x8A, 0x8B, 0x8C, 0x8D, 0x8E, 0x8F, 0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87
		};
		int[] ciphertext = {
			0xB0, 0xC3, 0x3C, 0xD7, 0xDB, 0x4D, 0x65, 0xA6, 0xBC, 0x49, 0xA8, 0x5A, 0x10, 0x77, 0xD7, 0x5D, 
			0x68, 0x55, 0xFC, 0xAF, 0xEA, 0x72, 0x93, 0xE4, 0x1C, 0x53, 0x85, 0xAB, 0x1B, 0x77, 0x54, 0xD2,  
			0x30, 0xE4, 0xAA, 0xFF, 0xE7, 0x80, 0xF7, 0x94, 0xE1, 0xBB, 0xEE, 0x70, 0x8C, 0xAF, 0xD8, 0xD5, 
			0x9C, 0xA8, 0x37, 0xB7, 0x42, 0x3B, 0x0F, 0x76, 0xBD, 0x14, 0x03, 0x67, 0x0D, 0x49, 0x63, 0xB3,  
			0x45, 0x1F, 0x2E, 0x3C, 0xE6, 0x1E, 0xA4, 0x8A, 0xB3, 0x60, 0x83, 0x2F, 0x92, 0x77, 0xD4, 0xFB, 
			0x0A, 0xAF, 0xC7, 0xA6, 0x5E, 0x12, 0xD6, 0x88, 0xC8, 0x90, 0x6E, 0x79, 0x01, 0x6D, 0x05, 0xD7,  
			0xB3, 0x16, 0x57, 0x0A, 0x15, 0xF4, 0x13, 0x33, 0x74, 0xE9, 0x8A, 0x28, 0x69, 0xF5, 0xD5, 0x0E, 
			0x57, 0xCE, 0x6F, 0x92, 0x47, 0x43, 0x2B, 0xCE, 0xDE, 0x7C, 0xDD, 0x77, 0x21, 0x51, 0x44, 0xDE 
		};
		
		threeFish1024.setTweak(new ByteArray(tweak));
		testAll(threeFish1024, key, plaintext, ciphertext);
	}
	
	@Test
	public final void runWhirlpool() {
		int[] key = { 
			00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,
			00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,
			00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,
			00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00 
		};
		int[] plaintext = { 
			0x61,0x62,0x63,0x80,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,
			0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x18 
		};
		int[] ciphertext = { 
			0x2f,0x46,0x2b,0x24,0xc6,0xf4,0x86,0xbb,0x16,0xb6,0x56,0x2c,0x73,0xb4,0x02,0x0b,
			0xf3,0x04,0x3e,0x3a,0x73,0x1b,0xce,0x72,0x1a,0xe1,0xb3,0x03,0xd9,0x7e,0x6d,0x4c,
			0x71,0x81,0xee,0xbd,0xb6,0xc5,0x7e,0x27,0x7d,0x0e,0x34,0x95,0x71,0x14,0xcb,0xd6,
			0xc7,0x97,0xfc,0x9d,0x95,0xd8,0xb5,0x82,0xd2,0x25,0x29,0x20,0x76,0xd4,0xee,0xed 
		};
		
		testAll(whirlpool, key, plaintext, ciphertext);
	}
	
	private void testAll(RoundBasedBlockCipher cipher, int[] keyArray, int[] plaintextArray, int[] ciphertextArray) {
		ByteArray ciphertext = new ByteArray(ciphertextArray);
		ByteArray plaintext = new ByteArray(plaintextArray);
		ByteArray key = new ByteArray(keyArray);
		testKeySchedule(cipher, key);
		testSingleRoundDecryption(cipher, ciphertext);
		testSingleRoundEncryption(cipher, plaintext);
		testFullDecryption(cipher, ciphertext);
		testFullEncryption(cipher, plaintext);
	}
	
	private void testFullDecryption(RoundBasedBlockCipher cipher, ByteArray ciphertext) {
		long startTime = System.nanoTime();
		
		for (int i = 0; i < numIterations; i++) {
			cipher.decrypt(ciphertext);
		}
		
		long endTime = System.nanoTime();
		logger.info("{0} full decryptions of {1} in {2}", numIterations, cipher.getName(), getSeconds(startTime, endTime));
	}
	
	private void testFullEncryption(RoundBasedBlockCipher cipher, ByteArray plaintext) {
		long startTime = System.nanoTime();
		
		for (int i = 0; i < numIterations; i++) {
			cipher.encrypt(plaintext);
		}

		long endTime = System.nanoTime();
		logger.info("{0} full encryptions of {1} in {2}", numIterations, cipher.getName(), getSeconds(startTime, endTime));
	}
	
	private void testKeySchedule(RoundBasedBlockCipher cipher, ByteArray key) {
		long startTime = System.nanoTime();
		
		for (int i = 0; i < numIterations; i++) {
			cipher.setKey(key);
		}
		
		long endTime = System.nanoTime();
		logger.info("{0} key schedules of {1} in {2}", numIterations, cipher.getName(), getSeconds(startTime, endTime));
	}
	
	private void testSingleRoundDecryption(RoundBasedBlockCipher cipher, ByteArray ciphertext) {
		long startTime = System.nanoTime();
		
		for (int i = 0; i < numIterations; i++) {
			cipher.decryptRounds(ciphertext, 1, 1);
		}

		long endTime = System.nanoTime();
		logger.info("{0} single round decryptions of {1} in {2}", numIterations, cipher.getName(), getSeconds(startTime, endTime));
	}
	
	private void testSingleRoundEncryption(RoundBasedBlockCipher cipher, ByteArray plaintext) {
		long startTime = System.nanoTime();
		
		for (int i = 0; i < numIterations; i++) {
			cipher.encryptRounds(plaintext, 1, 1);
		}
		
		long endTime = System.nanoTime();
		logger.info("{0} single round encryptions of {1} in {2}", numIterations, cipher.getName(), getSeconds(startTime, endTime));
	}
	
	private String getSeconds(long startTime, long endTime) {
		return Float.toString((float)(endTime - startTime) / 1000000000L);
	}
	
}