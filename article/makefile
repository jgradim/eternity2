TARGET=mpes

all:
	pdflatex $(TARGET).tex
	pdflatex $(TARGET).tex
	bibtex $(TARGET)
	pdflatex $(TARGET).tex

clean:
	rm -f $(TARGET).aux $(TARGET).log $(TARGET).pdf
