# Huffman Coding in Java

This Java program implements Huffman coding for data compression. It reads character probabilities from a file, builds a Huffman tree, generates Huffman codes, and provides encoding/decoding functionality for user input. The project consists of the following components:

- **Pair class**: Represents a character and its probability.
- **BinaryTree class**: Implements a binary tree with various tree operations.
- **Huffman class**: Manages the process of reading the probability file, building the Huffman tree, generating codes, and encoding/decoding text.

## Features

- **Reads probabilities**: Reads character probabilities from a file (`LettersProbability.txt`).
- **Builds Huffman tree**: Constructs a Huffman tree based on input probabilities.
- **Generates Huffman codes**: Derives Huffman codes from the built tree.
- **Encoding/Decoding**: Encodes and decodes user input based on the generated Huffman codes.

## Usage

1. **Input File**: The program expects a file with letters and their probabilities (e.g., `LettersProbability.txt`). The file should have each character and its corresponding probability on a new line, separated by spaces:
    ```
    A 0.2
    B 0.3
    C 0.5
    ```

2. **Run the Program**: Execute the `Huffman` class to start the program.
    - The program will prompt you to input the filename containing the character probabilities.
    - It will then build the Huffman tree and generate the corresponding Huffman codes.
    - Finally, you will be prompted to enter a string (uppercase letters only) to encode and decode.

## Example

```bash
Enter the name of the file with letters and probability: LettersProbability.txt
Building the Huffman tree...
Huffman coding completed.

Enter a line (uppercase letters only): ABC
Here’s the encoded line: 010011
The decoded line is: ABC
