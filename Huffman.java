//Full Name: Angel Mary Shyji

import java.io.*;
import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Step 1: Read the LettersProbability.txt file
            System.out.println("Huffman Coding");
            System.out.print("Enter the name of the file with letters and probability: ");
            String fileName = scanner.nextLine();
            List<BinaryTree<Pair>> trees = readProbabilities(fileName);
            System.out.println();

            // Step 2: Build the Huffman tree
            System.out.println("Building the Huffman tree...");
            BinaryTree<Pair> huffmanTree = buildHuffmanTree(trees);
            System.out.println("Huffman coding completed.");
            System.out.println();

            // Step 3: Derive Huffman codes
            String[] huffmanCodes = findEncoding(huffmanTree);

            // Step 4: Encode and Decode user input
            System.out.print("Enter a line (uppercase letters only): ");
            String input = scanner.nextLine();

            String encoded = encode(input, huffmanCodes);
            System.out.println("Here’s the encoded line: " + encoded);

            String decoded = decode(encoded, huffmanTree);
            System.out.println("The decoded line is: " + decoded);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read probabilities from file
    private static List<BinaryTree<Pair>> readProbabilities(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<BinaryTree<Pair>> trees = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            char letter = parts[0].charAt(0);
            double probability = Double.parseDouble(parts[1]);
            Pair pair = new Pair(letter, probability);
            BinaryTree<Pair> tree = new BinaryTree<>();
            tree.makeRoot(pair);
            trees.add(tree);
        }

        reader.close();
        Collections.sort(trees, Comparator.comparingDouble(t -> t.getData().getProb()));
        return trees;
    }

    // Build Huffman Tree
    private static BinaryTree<Pair> buildHuffmanTree(List<BinaryTree<Pair>> trees) {
        Queue<BinaryTree<Pair>> S = new LinkedList<>(trees);
        Queue<BinaryTree<Pair>> T = new LinkedList<>();

        while (S.size() + T.size() > 1) {
            BinaryTree<Pair> A = getSmallestTree(S, T);
            BinaryTree<Pair> B = getSmallestTree(S, T);

            Pair combinedPair = new Pair('\0', A.getData().getProb() + B.getData().getProb());
            BinaryTree<Pair> P = new BinaryTree<>();
            P.makeRoot(combinedPair);
            P.attachLeft(A);
            P.attachRight(B);

            T.add(P);
        }

        return T.poll();
    }

    private static BinaryTree<Pair> getSmallestTree(Queue<BinaryTree<Pair>> S, Queue<BinaryTree<Pair>> T) {
        if (S.isEmpty()) {
            return T.poll();
        } else if (T.isEmpty()) {
            return S.poll();
        } else if (S.peek().getData().getProb() <= T.peek().getData().getProb()) {
            return S.poll();
        } else {
            return T.poll();
        }
    }

    // Derive Huffman codes
    private static String[] findEncoding(BinaryTree<Pair> bt) {
        String[] result = new String[26]; // For letters A-Z
        findEncoding(bt, result, "");
        return result;
    }

    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix) {
        if (bt.getLeft() == null && bt.getRight() == null) {
            a[bt.getData().getValue() - 'A'] = prefix;
        } else {
            findEncoding(bt.getLeft(), a, prefix + "0");
            findEncoding(bt.getRight(), a, prefix + "1");
        }
    }

    // Encode a line of text
    private static String encode(String input, String[] huffmanCodes) {
        StringBuilder encoded = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == ' ') {
                encoded.append(" "); // Keep spaces as is
            } else {
                encoded.append(huffmanCodes[c - 'A']);
            }
        }

        return encoded.toString();
    }

    // Decode a line of text
    private static String decode(String encoded, BinaryTree<Pair> huffmanTree) {
        StringBuilder decoded = new StringBuilder();
        BinaryTree<Pair> current = huffmanTree;

        for (char c : encoded.toCharArray()) {
            if (c == ' ') {
                decoded.append(" ");
                current = huffmanTree; // Reset to the root
            } else {
                current = (c == '0') ? current.getLeft() : current.getRight();

                if (current.getLeft() == null && current.getRight() == null) {
                    decoded.append(current.getData().getValue());
                    current = huffmanTree; // Reset to the root
                }
            }
        }

        return decoded.toString();
    }
}
