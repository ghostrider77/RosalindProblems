# Compute the Score of a Linear Peptide
import itertools as it
import operator
import sys

from collections import Counter

from textbook_track.resources.utils import read_amino_acid_mass_table


def convert_to_intlist(line):
    return [int(elem) for elem in line.split()]


def calc_peptide_prefix_masses(peptide_masses):
    return list(it.accumulate(peptide_masses, operator.add, initial=0))


def calc_theoretical_linear_spectrum(peptide):
    prefix_masses = calc_peptide_prefix_masses(peptide)
    n = len(peptide)
    spectrum = [0]
    for start_ix in range(n):
        for end_ix in range(start_ix+1, n+1):
            subpeptide_mass = prefix_masses[end_ix] - prefix_masses[start_ix]
            spectrum.append(subpeptide_mass)
    return Counter(spectrum)


def calc_consistency_score(peptide, experimental_spectrum, mass_table):
    peptide_masses = tuple(mass_table[amino_acid] for amino_acid in peptide)
    cyclospectrum = calc_theoretical_linear_spectrum(peptide_masses)
    return sum(min(experimental_spectrum.get(mass, 0), count) for mass, count in cyclospectrum.items())


def main():
    data = sys.stdin.read().splitlines()
    peptide = data[0]
    spectrum = Counter(convert_to_intlist(data[1]))
    mass_table = read_amino_acid_mass_table()
    result = calc_consistency_score(peptide, spectrum, mass_table)
    print(result)


if __name__ == '__main__':
    main()
