package org.alcibiade.eternity.editor.solver.diamonds;

public class Boards {
	
	static Peca[][] Tab16x16= {
			{new Peca(5, 0, 8, 8), new Peca(0, 19, 9, 1), new Peca(9, 3, 18, 1), new Peca(19, 2, 2, 18), new Peca(9, 21, 3, 19), new Peca(10, 20, 15, 4), new Peca(11, 23, 16, 14), new Peca(17, 16, 5, 17), new Peca(1, 22, 17, 7), new Peca(1, 7, 19, 0), new Peca(0, 10, 22, 8), new Peca(19, 6, 20, 6), new Peca(20, 5, 0, 20), new Peca(21, 10, 11, 4), new Peca(19, 13, 8, 23), new Peca(18, 20, 10, 14) },
			{new Peca(1, 13, 21, 11), new Peca(6, 2, 22, 9), new Peca(3, 17, 23, 5), new Peca(0, 3, 8, 18), new Peca(13, 4, 9, 22), new Peca(15, 19, 0, 4), new Peca(13, 15, 5, 20), new Peca(16, 8, 1, 7), new Peca(5, 1, 0, 8), new Peca(8, 0, 22, 19), new Peca(20, 1, 21, 2), new Peca(17, 7, 11, 18), new Peca(8, 0, 11, 13), new Peca(8, 1, 20, 0), new Peca(8, 17, 12, 14), new Peca(15, 7, 18, 23) },
			{new Peca(16, 8, 5, 23), new Peca(2, 10, 11, 23), new Peca(6, 5, 22, 0), new Peca(14, 18, 12, 9), new Peca(19, 6, 17, 10), new Peca(9, 19, 6, 18), new Peca(6, 0, 0, 1), new Peca(16, 9, 16, 17), new Peca(17, 22, 9, 17), new Peca(21, 7, 15, 11), new Peca(6, 1, 4, 6), new Peca(13, 0, 18, 15), new Peca(3, 2,
			 15, 14), new Peca(3, 13, 9, 0), new Peca(14, 15, 4, 11), new Peca(21, 4, 20, 0) },
			{new Peca(12, 12, 4, 16), new Peca(8, 17, 21, 6), new Peca(3, 3, 4, 0), new Peca(2, 22, 19, 16), new Peca(22, 20, 2, 11), new Peca(14, 23, 6, 6), new Peca(12, 20, 6, 21), new Peca(12, 18, 18, 12), new Peca(6, 22, 6, 7), new Peca(11, 20, 14, 21), new Peca(13, 1, 22, 18), new Peca(2, 7, 20, 5), new Peca(13, 22, 12, 4), new Peca(22, 3, 16, 2), new Peca(14, 3, 19, 14), new Peca(22, 4, 15, 1) },
			{new Peca(8, 8, 14, 15), new Peca(3, 18, 18, 8), new Peca(23, 21, 5, 21), new Peca(4, 13, 5, 11), new Peca(4, 23, 4, 0), new Peca(5, 5, 15, 0), new Peca(18, 12, 3, 8), new Peca(7, 15, 23, 15), new Peca(17, 11, 16, 19), new Peca(17, 0, 21, 6), new Peca(22, 19, 11, 6), new Peca(10, 4, 5, 0), new Peca(11, 8, 15, 23), new Peca(6, 1, 12, 23), new Peca(22, 8, 9, 0), new Peca(20, 6, 20, 11) },
			{new Peca(8, 8, 18, 9), new Peca(16, 8, 13, 14), new Peca(2, 5, 4, 13), new Peca(10, 10, 16, 18), new Peca(1, 8, 0, 17), new Peca(13, 8, 23, 11), new Peca(13, 16, 12, 14), new Peca(8, 14, 6, 8), new Peca(4, 22, 0, 13), new Peca(19,
			 13, 14, 11), new Peca(7, 13, 9, 3), new Peca(13, 18, 4, 20), new Peca(19, 23, 23, 14), new Peca(3, 13, 6, 13), new Peca(13, 4, 21, 12), new Peca(19, 0, 0, 10) },
			{new Peca(17, 0, 15, 16), new Peca(2, 2, 19, 2), new Peca(22, 20, 17, 2), new Peca(9, 14, 4, 6), new Peca(13, 6, 0, 21), new Peca(23, 11, 14, 22), new Peca(10, 14, 0, 13), new Peca(8, 5, 1, 4), new Peca(11, 17, 8, 10), new Peca(5, 19, 15, 22), new Peca(22, 8, 14, 2), new Peca(14, 18, 15, 1), new Peca(5, 11, 21, 0), new Peca(0, 17, 11, 10), new Peca(13, 12, 5, 8), new Peca(16, 14, 18, 2) },
			{new Peca(16, 15, 10, 23), new Peca(11, 22, 11, 22), new Peca(21, 16, 6, 21), new Peca(22, 11, 6, 13), new Peca(6, 13, 12, 13), new Peca(8, 11, 10, 10), new Peca(5, 7, 8, 4), new Peca(6, 11, 23, 15), new Peca(22, 22, 3, 11), new Peca(0, 11, 12, 10), new Peca(1, 20, 4, 15), new Peca(12, 23, 16, 20), new Peca(14, 4, 6, 9), new Peca(1, 1, 1, 4), new Peca(8, 16, 8, 4), new Peca(8, 5, 18, 8) },
			{new Peca(20, 12, 23, 18), new Peca(8, 4, 5, 14), new Peca(4, 11, 21, 16), new Peca(13, 11, 0, 18), new Peca(14, 10, 4, 11), new Peca(22, 12, 13, 8),
			 new Peca(19, 10, 18, 0), new Peca(11, 12, 4, 1), new Peca(20, 9, 21, 5), new Peca(7, 12, 7, 8), new Peca(19, 23, 6, 20), new Peca(23, 11, 4, 4), new Peca(16, 20, 13, 15), new Peca(22, 6, 3, 22), new Peca(2, 11, 12, 4), new Peca(9, 8, 4, 7) },
			{new Peca(11, 4, 7, 3), new Peca(13, 14, 19, 14), new Peca(6, 12, 11, 4), new Peca(16, 16, 21, 20), new Peca(14, 19, 18, 9), new Peca(5, 14, 1, 3), new Peca(10, 9, 4, 21), new Peca(6, 14, 7, 23), new Peca(13, 19, 13, 16), new Peca(6, 15, 11, 19), new Peca(9, 4, 19, 13), new Peca(9, 19, 6, 23), new Peca(20, 17, 7, 6), new Peca(3, 0, 12, 13), new Peca(6, 13, 2, 15), new Peca(21, 0, 1, 19) },
			{new Peca(11, 4, 5, 3), new Peca(23, 10, 14, 18), new Peca(4, 8, 16, 5), new Peca(20, 3, 22, 17), new Peca(11, 22, 16, 12), new Peca(21, 4, 21, 6), new Peca(5, 5, 5, 10), new Peca(21, 13, 21, 20), new Peca(3, 3, 10, 21), new Peca(16, 7, 9, 14), new Peca(16, 4, 18, 8), new Peca(4, 0, 22, 6), new Peca(12, 3, 10, 6), new Peca(3, 18, 23, 17), new Peca(2, 23, 16, 11), new Peca(14, 2, 8, 21) },
			{new Peca(8, 13, 13, 8), new Peca(4, 13, 3, 6), new Peca(1, 11, 9, 4),
			 new Peca(9, 3, 22, 2), new Peca(12, 10, 8, 13), new Peca(16, 9, 22, 13), new Peca(6, 20, 1, 8), new Peca(14, 8, 21, 0), new Peca(13, 1, 0, 22), new Peca(10, 9, 12, 0), new Peca(21, 9, 23, 2), new Peca(4, 23, 17, 1), new Peca(12, 23, 8, 19), new Peca(6, 4, 20, 10), new Peca(16, 17, 17, 1), new Peca(15, 19, 13, 7) },
			{new Peca(20, 13, 0, 21), new Peca(22, 20, 2, 12), new Peca(22, 2, 5, 9), new Peca(12, 4, 9, 12), new Peca(18, 0, 0, 1), new Peca(9, 16, 21, 0), new Peca(12, 9, 23, 2), new Peca(20, 20, 20, 12), new Peca(23, 17, 22, 18), new Peca(19, 19, 23, 3), new Peca(21, 23, 15, 22), new Peca(11, 0, 17, 4), new Peca(1, 0, 22, 8), new Peca(19, 17, 0, 4), new Peca(16, 7, 14, 1), new Peca(22, 0, 21, 7) },
			{new Peca(22, 0, 17, 1), new Peca(16, 0, 0, 18), new Peca(8, 1, 21, 18), new Peca(13, 15, 5, 11), new Peca(20, 22, 6, 11), new Peca(23, 4, 1, 9), new Peca(19, 2, 5, 14), new Peca(22, 12, 20, 16), new Peca(1, 10, 1, 14), new Peca(4, 7, 19, 12), new Peca(19, 1, 3, 0), new Peca(23, 10, 20, 10), new Peca(0, 13, 7, 3), new Peca(17, 1, 21, 1), new Peca(21, 19, 5, 1), new Peca(20, 4, 9, 14)
			 },
			{new Peca(17, 11, 19, 10), new Peca(8, 4, 6, 21), new Peca(3, 4, 16, 14), new Peca(21, 0, 16, 7), new Peca(3, 5, 2, 12), new Peca(11, 14, 19, 8), new Peca(8, 10, 14, 4), new Peca(2, 5, 22, 3), new Peca(17, 4, 4, 22), new Peca(20, 13, 10, 0), new Peca(11, 14, 19, 3), new Peca(5, 10, 10, 0), new Peca(1, 0, 9, 22), new Peca(10, 23, 17, 23), new Peca(8, 4, 0, 6), new Peca(23, 1, 10, 9) },
			{new Peca(6, 17, 2, 13), new Peca(15, 14, 9, 22), new Peca(18, 18, 0, 4), new Peca(15, 20, 6, 3), new Peca(1, 23, 22, 0), new Peca(1, 13, 8, 21), new Peca(17, 0, 8, 22), new Peca(17, 3, 20, 5), new Peca(18, 13, 13, 0), new Peca(8, 18, 6, 23), new Peca(5, 11, 10, 23), new Peca(4, 20, 21, 16), new Peca(1, 18, 13, 17), new Peca(18, 14, 10, 4), new Peca(6, 13, 10, 21), new Peca(6, 6, 0, 6) }
			};
	
	static Peca[][] Tab14x14= {
		{new Peca(20, 6, 0, 6), new Peca(11, 2, 0, 7), new Peca(1, 9, 3, 13), new Peca(2, 9, 2, 8), new Peca(12, 12, 20, 11), new Peca(16, 11, 2, 2), new Peca(14, 12, 14, 20), new Peca(4, 0, 9, 20), new Peca(15, 12, 16, 13), new Peca(10, 0, 18, 18), new Peca(1, 13, 0, 0), new Peca(10, 1, 15, 17), new Peca(6, 13, 10, 0), new Peca(2, 10, 11, 8) },
		{new Peca(5, 6, 18, 9), new Peca(11, 15, 11, 19), new Peca(12, 0, 1, 2), new Peca(12, 15, 6, 2), new Peca(0, 13, 4, 5), new Peca(2, 12, 15, 18), new Peca(12, 14, 2, 17), new Peca(4, 18, 2, 9), new Peca(20, 16, 12, 6), new Peca(2, 6, 15, 20), new Peca(8, 12, 9, 1), new Peca(18, 16, 13, 20), new Peca(11, 16, 14, 18), new Peca(17, 16, 8, 1) },
		{new Peca(10, 0, 18, 12), new Peca(7, 10, 17, 11), new Peca(10, 17, 3, 12), new Peca(6, 0, 2, 7), new Peca(16, 18, 9, 4), new Peca(17, 8, 9, 2), new Peca(1, 6, 6, 3), new Peca(15, 2, 3, 6), new Peca(7, 0, 12, 18), new Peca(0, 12, 12, 1), new Peca(10, 6, 5, 16), new Peca(16, 9, 2, 6), new Peca(9, 20, 8, 17), new Peca(18, 12, 10, 3) },
		{new Peca(12, 8, 0, 9), new Peca(5, 10, 16, 0), new Peca(4, 3, 19, 10), new Peca(3, 19, 1, 2), new Peca(6, 13, 5, 20), new Peca(20, 8, 20, 4), new Peca(15, 1, 13, 20), new Peca(15, 5, 0, 12), new Peca(9, 6, 16, 16), new Peca(16, 11, 17, 16), new Peca(14, 20, 12, 16), new Peca(16, 13, 3, 11), new Peca(7, 5, 5, 10), new Peca(6, 8, 19, 20) },
		{new Peca(20, 20, 12, 13), new Peca(4, 6, 13, 11), new Peca(12, 3, 16, 10), new Peca(2, 7, 13, 10), new Peca(2, 8, 7, 13), new Peca(15, 17, 11, 12), new Peca(16, 16, 17, 6), new Peca(19, 1, 0, 12), new Peca(6, 0, 2, 8), new Peca(2, 14, 6, 20), new Peca(10, 7, 13, 6), new Peca(19, 1, 10, 0), new Peca(11, 11, 2, 20), new Peca(6, 7, 2, 18) },
		{new Peca(16, 0, 15, 2), new Peca(12, 17, 9, 9), new Peca(0, 10, 4, 12), new Peca(1, 9, 15, 5), new Peca(14, 16, 11, 18), new Peca(1, 13, 4, 5), new Peca(8, 16, 3, 20), new Peca(4, 6, 7, 20), new Peca(11, 20, 2, 17), new Peca(1, 14, 6, 17), new Peca(16, 20, 8, 6), new Peca(11, 0, 5, 9), new Peca(20, 9, 4, 11), new Peca(12, 0, 0, 15) },
		{new Peca(18, 2, 11, 3), new Peca(2, 0, 12, 11), new Peca(18, 12, 17, 13), new Peca(5, 2, 8, 15), new Peca(16, 20, 19, 5), new Peca(2, 3, 8, 6), new Peca(9, 16, 0, 16), new Peca(2, 2, 0, 12), new Peca(7, 11, 0, 10), new Peca(20, 20, 19, 1), new Peca(12, 8, 8, 15), new Peca(9, 2, 20, 5), new Peca(7, 18, 6, 6), new Peca(4, 15, 2, 16) },
		{new Peca(20, 5, 7, 2), new Peca(16, 18, 4, 17), new Peca(2, 5, 6, 12), new Peca(12, 15, 7, 9), new Peca(9, 4, 2, 15), new Peca(12, 9, 1, 14), new Peca(14, 2, 18, 6), new Peca(4, 17, 1, 9), new Peca(2, 18, 0, 5), new Peca(9, 5, 9, 1), new Peca(2, 15, 1, 13), new Peca(0, 4, 1, 0), new Peca(15, 20, 7, 14), new Peca(2, 9, 15, 18) },
		{new Peca(4, 17, 5, 8), new Peca(10, 4, 10, 11), new Peca(15, 11, 6, 1), new Peca(16, 5, 13, 16), new Peca(0, 8, 1, 12), new Peca(3, 20, 3, 8), new Peca(2, 3, 19, 8), new Peca(16, 5, 19, 18), new Peca(1, 16, 13, 17), new Peca(0, 9, 6, 12), new Peca(6, 9, 13, 9), new Peca(0, 1, 10, 19), new Peca(7, 7, 16, 6), new Peca(7, 1, 1, 14) },
		{new Peca(9, 18, 2, 9), new Peca(17, 12, 0, 10), new Peca(5, 15, 8, 17), new Peca(15, 2, 11, 3), new Peca(19, 0, 16, 14), new Peca(12, 19, 1, 0), new Peca(11, 4, 20, 16), new Peca(8, 13, 8, 0), new Peca(2, 13, 2, 2), new Peca(19, 13, 16, 4), new Peca(15, 17, 9, 10), new Peca(16, 12, 2, 16), new Peca(20, 5, 9, 12), new Peca(3, 10, 0, 2) },
		{new Peca(11, 3, 4, 0), new Peca(7, 1, 1, 20), new Peca(15, 12, 8, 0), new Peca(7, 1, 5, 3), new Peca(12, 7, 16, 9), new Peca(19, 1, 13, 12), new Peca(1, 12, 18, 4), new Peca(15, 20, 4, 11), new Peca(12, 18, 1, 9), new Peca(13, 15, 3, 13), new Peca(15, 3, 17, 0), new Peca(9, 10, 20, 20), new Peca(1, 1, 1, 5), new Peca(17, 13, 16, 16) },
		{new Peca(15, 0, 7, 8), new Peca(3, 4, 13, 1), new Peca(13, 17, 8, 3), new Peca(16, 17, 7, 18), new Peca(10, 15, 18, 0), new Peca(15, 13, 8, 12), new Peca(2, 12, 0, 10), new Peca(9, 0, 6, 16), new Peca(4, 12, 15, 8), new Peca(3, 9, 1, 12), new Peca(12, 8, 17, 16), new Peca(11, 9, 20, 6), new Peca(17, 4, 4, 6), new Peca(13, 9, 20, 12) },
		{new Peca(7, 12, 7, 5), new Peca(4, 20, 20, 9), new Peca(1, 11, 10, 5), new Peca(2, 2, 10, 7), new Peca(18, 14, 2, 19), new Peca(15, 9, 13, 20), new Peca(9, 12, 14, 19), new Peca(16, 5, 0, 2), new Peca(15, 9, 19, 14), new Peca(20, 9, 2, 19), new Peca(19, 10, 8, 12), new Peca(15, 0, 8, 6), new Peca(11, 5, 2, 16), new Peca(0, 11, 19, 7) },
		{new Peca(11, 13, 15, 11), new Peca(8, 8, 1, 16), new Peca(16, 15, 19, 5), new Peca(15, 8, 0, 16), new Peca(18, 16, 16, 9), new Peca(4, 6, 15, 0), new Peca(16, 6, 8, 12), new Peca(8, 8, 15, 0), new Peca(0, 19, 1, 9), new Peca(0, 1, 5, 9), new Peca(15, 1, 20, 20), new Peca(13, 4, 5, 20), new Peca(17, 2, 0, 0), new Peca(19, 7, 11, 20) }
		};
	
	static Peca[][] Tab12x12= {
			{new Peca(8, 3, 4, 4), new Peca(12, 10, 8, 7), new Peca(6, 9, 10, 7), new Peca(7, 14, 18, 5), new Peca(4, 0, 18, 3), new Peca(4, 12, 4, 10), new Peca(4, 15, 3, 0), new Peca(5, 14, 2, 4), new Peca(11, 0, 8, 14), new Peca(7, 1, 10, 17), new Peca(12, 4, 17, 8), new Peca(14, 7, 12, 7) },
			{new Peca(7, 7, 12, 9), new Peca(12, 13, 0, 10), new Peca(18, 18, 0, 10), new Peca(8, 12, 8, 13), new Peca(11, 15, 0, 10), new Peca(1, 4, 2, 0), new Peca(9, 9, 0, 8), new Peca(13, 6, 4, 7), new Peca(7, 10, 15, 17), new Peca(6, 7, 10, 18), new Peca(5, 0, 11, 7), new Peca(18, 9, 5, 18) },
			{new Peca(16, 14, 14, 9), new Peca(9, 17, 10, 4), new Peca(10, 14, 14, 9), new Peca(8, 0, 15, 18), new Peca(2, 5, 7, 10), new Peca(10, 18, 2, 13), new Peca(14, 12, 12, 10), new Peca(2, 16, 14, 17), new Peca(6, 10, 3, 8), new Peca(15, 14, 11, 9), new Peca(12, 4, 16, 9), new Peca(18, 17, 3, 9) },
			{new Peca(4, 1, 0, 10), new Peca(15, 17, 17, 12), new Peca(0, 2, 18, 10), new Peca(1, 2, 17, 8), new Peca(18, 2, 4, 1), new Peca(3, 16, 10, 6), new Peca(3, 8, 7, 7), new Peca(17, 18, 15, 11), new Peca(12, 3, 4, 10), new Peca(17, 14, 17, 16), new Peca(18, 18, 11, 0), new Peca(3, 5, 4, 16) },
			{new Peca(16, 7, 4, 0), new Peca(12, 11, 8, 9), new Peca(7, 14, 16, 16), new Peca(7, 7, 7, 11), new Peca(0, 17, 17, 18), new Peca(8, 3, 0, 18), new Peca(2, 16, 17, 0), new Peca(8, 5, 12, 6), new Peca(15, 2, 16, 16), new Peca(4, 9, 8, 15), new Peca(17, 10, 0, 16), new Peca(5, 2, 7, 6) },
			{new Peca(3, 2, 9, 1), new Peca(6, 4, 2, 18), new Peca(17, 4, 16, 1), new Peca(17, 9, 14, 8), new Peca(5, 18, 13, 6), new Peca(10, 10, 17, 17), new Peca(13, 4, 14, 0), new Peca(7, 17, 3, 17), new Peca(17, 4, 5, 4), new Peca(5, 1, 3, 11), new Peca(8, 18, 9, 1), new Peca(7, 5, 12, 9) },
			{new Peca(11, 10, 13, 14), new Peca(9, 9, 18, 7), new Peca(17, 3, 18, 18), new Peca(0, 14, 18, 16), new Peca(11, 3, 8, 13), new Peca(3, 10, 9, 18), new Peca(13, 5, 10, 0), new Peca(10, 1, 18, 9), new Peca(4, 16, 2, 3), new Peca(10, 5, 13, 10), new Peca(15, 15, 5, 14), new Peca(0, 11, 13, 6) },
			{new Peca(4, 18, 7, 5), new Peca(13, 11, 2, 17), new Peca(12, 13, 16, 11), new Peca(18, 11, 0, 5), new Peca(6, 8, 3, 3), new Peca(6, 1, 12, 5), new Peca(17, 7, 5, 4), new Peca(17, 15, 14, 13), new Peca(14, 2, 13, 9), new Peca(0, 9, 4, 18), new Peca(12, 15, 6, 17), new Peca(7, 11, 0, 14) },
			{new Peca(6, 7, 11, 5), new Peca(8, 6, 17, 2), new Peca(3, 0, 15, 18), new Peca(6, 8, 6, 14), new Peca(0, 3, 11, 10), new Peca(5, 3, 6, 4), new Peca(18, 1, 7, 17), new Peca(10, 13, 14, 0), new Peca(13, 0, 0, 13), new Peca(17, 7, 16, 17), new Peca(13, 0, 18, 14), new Peca(4, 10, 0, 2) },
			{new Peca(9, 16, 4, 13), new Peca(8, 9, 18, 8), new Peca(13, 8, 3, 5), new Peca(7, 17, 11, 16), new Peca(6, 16, 0, 11), new Peca(12, 14, 7, 12), new Peca(15, 8, 1, 13), new Peca(12, 5, 11, 10), new Peca(6, 2, 0, 0), new Peca(6, 11, 4, 1), new Peca(7, 18, 10, 10), new Peca(2, 6, 1, 6) },
			{new Peca(15, 4, 17, 13), new Peca(15, 0, 12, 14), new Peca(3, 18, 0, 3), new Peca(15, 10, 15, 17), new Peca(15, 4, 16, 4), new Peca(15, 3, 15, 5), new Peca(16, 5, 0, 15), new Peca(1, 13, 9, 2), new Peca(10, 4, 4, 12), new Peca(0, 4, 2, 0), new Peca(5, 5, 0, 5), new Peca(17, 8, 18, 4) },
			{new Peca(0, 3, 12, 8), new Peca(14, 6, 12, 1), new Peca(8, 5, 8, 4), new Peca(3, 4, 13, 7), new Peca(15, 0, 0, 11), new Peca(9, 9, 18, 1), new Peca(13, 4, 16, 7), new Peca(14, 18, 0, 5), new Peca(10, 0, 18, 7), new Peca(8, 2, 0, 2), new Peca(11, 11, 11, 4), new Peca(12, 0, 10, 18) }};

	static Peca[][] Tab10x10= {
			{new Peca(0, 15, 10, 1), new Peca(0, 9, 8, 5), new Peca(11, 11, 2, 11), new Peca(7, 10, 4, 0), new Peca(13, 5, 8, 0), new Peca(4, 6, 15, 9), new Peca(9, 1, 9, 8), new Peca(5, 11, 0, 3), new Peca(14, 9, 7, 1), new Peca(14, 10, 7, 5) },
			{new Peca(8, 14, 12, 14), new Peca(10, 5, 15, 1), new Peca(15, 10, 15, 4), new Peca(0, 1, 14, 4), new Peca(1, 15, 15, 12), new Peca(15, 3, 0, 4), new Peca(12, 13, 0, 9), new Peca(2, 0, 3, 3), new Peca(2, 7, 9, 0), new Peca(6, 1, 5, 15) },
			{new Peca(0, 2, 8, 15), new Peca(9, 11, 4, 3), new Peca(2, 10, 6, 2), new Peca(9, 6, 14, 5), new Peca(5, 13, 11, 11), new Peca(13, 9, 6, 14), new Peca(9, 11, 6, 1), new Peca(9, 5, 6, 12), new Peca(4, 13, 8, 6), new Peca(8, 1, 14, 11) },
			{new Peca(14, 6, 4, 12), new Peca(7, 0, 14, 15), new Peca(11, 5, 10, 14), new Peca(5, 14, 0, 12), new Peca(9, 4, 13, 11), new Peca(0, 13, 11, 1), new Peca(10, 14, 4, 6), new Peca(12, 12, 3, 13), new Peca(5, 5, 0, 2), new Peca(0, 4, 13, 12) },
			{new Peca(4, 12, 5, 10), new Peca(9, 7, 5, 2), new Peca(13, 12, 6, 15), new Peca(13, 1, 8, 6), new Peca(7, 4, 11, 6), new Peca(5, 14, 14, 14), new Peca(9, 1, 3, 2), new Peca(3, 13, 13, 15), new Peca(5, 12, 0, 0), new Peca(1, 15, 9, 9) },
			{new Peca(0, 5, 8, 6), new Peca(14, 13, 0, 1), new Peca(14, 9, 1, 4), new Peca(14, 9, 10, 14), new Peca(8, 13, 14, 11), new Peca(13, 14, 0, 1), new Peca(4, 14, 4, 0), new Peca(7, 0, 0, 11), new Peca(9, 8, 13, 2), new Peca(0, 0, 7, 1) },
			{new Peca(4, 8, 1, 14), new Peca(14, 5, 8, 3), new Peca(2, 14, 8, 15), new Peca(15, 13, 0, 11), new Peca(6, 1, 14, 0), new Peca(15, 12, 2, 8), new Peca(9, 4, 5, 15), new Peca(11, 15, 1, 4), new Peca(4, 2, 14, 1), new Peca(0, 14, 15, 9) },
			{new Peca(9, 9, 13, 8), new Peca(6, 13, 4, 14), new Peca(14, 10, 5, 0), new Peca(11, 4, 13, 13), new Peca(14, 8, 9, 14), new Peca(4, 1, 5, 7), new Peca(9, 14, 11, 0), new Peca(1, 7, 2, 12), new Peca(7, 2, 15, 3), new Peca(4, 7, 7, 13) },
			{new Peca(1, 14, 12, 11), new Peca(14, 3, 0, 5), new Peca(3, 11, 2, 7), new Peca(7, 2, 0, 7), new Peca(4, 14, 11, 5), new Peca(8, 13, 15, 7), new Peca(10, 10, 5, 6), new Peca(9, 0, 7, 7), new Peca(0, 0, 12, 2), new Peca(8, 15, 9, 0) },
			{new Peca(13, 7, 0, 13), new Peca(6, 4, 6, 12), new Peca(9, 15, 10, 10), new Peca(6, 0, 6, 13), new Peca(6, 1, 11, 5), new Peca(14, 14, 15, 5), new Peca(8, 15, 12, 15), new Peca(9, 13, 7, 0), new Peca(11, 1, 4, 14), new Peca(5, 4, 10, 15) }
			};
	
	static Peca[][] Tab10x10_= {
			{new Peca(4, 10, 4, 14), new Peca(10, 9, 5, 15), new Peca(0, 2, 9, 2), new Peca(14, 15, 12, 9), new Peca(10, 0, 13, 3), new Peca(5, 12, 11, 8), new Peca(1, 14, 0, 2), new Peca(0, 14, 9, 10), new Peca(11, 14, 8, 13), new Peca(15, 3, 14, 11) },
			{new Peca(6, 7, 1, 12), new Peca(13, 0, 2, 12), new Peca(2, 0, 3, 6), new Peca(4, 15, 1, 14), new Peca(8, 3, 9, 0), new Peca(13, 15, 8, 1), new Peca(13, 4, 11, 1), new Peca(6, 2, 11, 1), new Peca(3, 9, 13, 14), new Peca(8, 1, 14, 1) },
			{new Peca(8, 13, 11, 1), new Peca(12, 14, 5, 10), new Peca(8, 6, 8, 7), new Peca(10, 10, 1, 6), new Peca(0, 10, 5, 10), new Peca(0, 2, 9, 15), new Peca(12, 5, 3, 7), new Peca(2, 11, 4, 6), new Peca(0, 8, 13, 1), new Peca(2, 6, 4, 7) },
			{new Peca(1, 12, 13, 12), new Peca(0, 0, 13, 14), new Peca(1, 14, 9, 13), new Peca(8, 2, 13, 12), new Peca(5, 1, 2, 0), new Peca(4, 2, 8, 0), new Peca(6, 7, 13, 4), new Peca(13, 7, 4, 7), new Peca(11, 4, 8, 12), new Peca(11, 0, 14, 14) },
			{new Peca(0, 8, 6, 2), new Peca(6, 12, 15, 3), new Peca(11, 8, 13, 1), new Peca(6, 7, 13, 5), new Peca(14, 4, 9, 15), new Peca(3, 0, 1, 5), new Peca(1, 3, 10, 4), new Peca(5, 12, 13, 11), new Peca(12, 12, 11, 7), new Peca(9, 12, 5, 9) },
			{new Peca(0, 2, 3, 8), new Peca(6, 14, 13, 5), new Peca(1, 15, 13, 6), new Peca(5, 14, 5, 15), new Peca(13, 13, 0, 13), new Peca(0, 0, 1, 5), new Peca(0, 9, 6, 10), new Peca(8, 0, 0, 10), new Peca(12, 11, 13, 14), new Peca(13, 6, 10, 8) },
			{new Peca(9, 12, 1, 2), new Peca(3, 2, 10, 7), new Peca(10, 9, 10, 2), new Peca(13, 13, 0, 7), new Peca(5, 4, 0, 11), new Peca(6, 3, 8, 12), new Peca(9, 11, 14, 15), new Peca(6, 13, 6, 15), new Peca(1, 2, 0, 10), new Peca(0, 15, 5, 8) },
			{new Peca(1, 5, 8, 14), new Peca(11, 2, 4, 13), new Peca(9, 7, 13, 2), new Peca(7, 7, 6, 2), new Peca(10, 0, 7, 14), new Peca(14, 10, 7, 14), new Peca(11, 10, 11, 9), new Peca(2, 0, 3, 11), new Peca(1, 11, 1, 0), new Peca(1, 1, 0, 8) },
			{new Peca(2, 7, 0, 1), new Peca(4, 10, 0, 2), new Peca(10, 13, 2, 4), new Peca(7, 0, 9, 3), new Peca(1, 6, 6, 3), new Peca(1, 11, 10, 10), new Peca(9, 0, 8, 14), new Peca(6, 14, 10, 4), new Peca(13, 1, 13, 11), new Peca(0, 10, 15, 3) },
			{new Peca(10, 8, 0, 0), new Peca(14, 2, 15, 1), new Peca(4, 2, 5, 15), new Peca(15, 7, 12, 5), new Peca(13, 11, 2, 13), new Peca(13, 11, 5, 3), new Peca(13, 1, 6, 6), new Peca(15, 1, 0, 10), new Peca(11, 9, 3, 12), new Peca(7, 8, 11, 1) }
			};
	
	static Peca[][] Tab6x6= {
		{ 
		new Peca(0, 3, 2, 0),
		new Peca(0, 1, 4, 3),
		new Peca(0, 5, 2, 1),
		new Peca(0, 4, 3, 5),
		new Peca(0, 2, 3, 4),
		new Peca(0, 0, 1, 2)
		},
		{
		new Peca(2, 3, 1, 0),
		new Peca(4, 5, 2, 3),
		new Peca(2, 2, 1, 5),
		new Peca(3, 5, 4, 2),
		new Peca(3, 6, 2, 5),
		new Peca(1, 0, 4, 6),
		},
		{
		new Peca(1, 6, 4, 0),
		new Peca(2, 3, 3, 6),
		new Peca(1, 3, 1, 3),
		new Peca(4, 4, 5, 3),
		new Peca(2, 1, 3, 4),
		new Peca(4, 0, 3, 1),
		},
		{
		new Peca(4, 1, 5, 0),
		new Peca(3, 4, 3, 1),
		new Peca(1, 3, 5, 4),
		new Peca(5, 6, 2, 3),
		new Peca(3, 5, 3, 6),
		new Peca(3, 0, 1, 5),
		},
		{
		new Peca(5, 4, 3, 0),
		new Peca(3, 1, 6, 4),
		new Peca(5, 4, 3, 1),
		new Peca(2, 1, 5, 4),
		new Peca(3, 2, 4, 1),
		new Peca(1, 0, 3, 2),
		},
		{
		new Peca(3, 2, 0, 0),
		new Peca(6, 3, 0, 2),
		new Peca(3, 4, 0, 3),
		new Peca(5, 2, 0, 4),
		new Peca(4, 6, 0, 2),
		new Peca(3, 0, 0, 6),
		}
		};

	static Peca[][] Tab5x5= {
		{new Peca(0, 3, 1, 0), new Peca(0, 2, 1, 3), new Peca(0, 3, 4, 2), new Peca(0, 2, 1, 3), new Peca(0, 0, 3, 2) },
		{new Peca(1, 3, 4, 0), new Peca(1, 4, 1, 3), new Peca(4, 2, 1, 4), new Peca(1, 4, 2, 2), new Peca(3, 0, 3, 4) },
		{new Peca(4, 2, 2, 0), new Peca(1, 3, 3, 2), new Peca(1, 4, 1, 3), new Peca(2, 3, 4, 4), new Peca(3, 0, 2, 3) },
		{new Peca(2, 4, 3, 0), new Peca(3, 2, 1, 4), new Peca(1, 4, 1, 2), new Peca(4, 1, 3, 4), new Peca(2, 0, 1, 1) },
		{new Peca(3, 4, 0, 0), new Peca(1, 2, 0, 4), new Peca(1, 3, 0, 2), new Peca(3, 4, 0, 3), new Peca(1, 0, 0, 4) }
		};

	static Peca[][] Tab2x2 = {{new Peca(0,1,2,0), new Peca(0,0,3,1)}, {new Peca(2,4,0,0), new Peca(3,0,0,4)}};
	static Peca[][] Tab2x2_ = {{new Peca(0,1,2,0), new Peca(0,0,2,1)}, {new Peca(2,4,0,0), new Peca(2,0,0,4)}};
}
