from hmac import compare_digest
from multiprocessing import connection
import qiskit

def hw4_1_response(circuit, outside_qubit, qubit_pair, bell_pair_start):
    # Put your code here (spaces for indentation)
    circuit.cx(outside_qubit, qubit_pair[0])
    circuit.h(outside_qubit)
    circuit.cx(qubit_pair[0], qubit_pair[1])
    circuit.cz(outside_qubit, qubit_pair[1])

    if bell_pair_start[0] == "1":
        circuit.z(qubit_pair[1])
    if bell_pair_start[1] == "1":
        circuit.x(qubit_pair[1])
    # End Code
    return circuit


def hw4_2_response(circuit, n, codes):
    # Put your code to find the entangled qubits here

    print(codes)

    circuit = qiskit.QuantumCircuit(n + 1)
    for code in codes:
        controls = []
        notcontrols = []
        allnums = list(range(n))
        for i in range(len(code)):
            if(code[i] == "1"):
                controls.append(i)
            else:
                notcontrols.append(i)

        if(notcontrols):
            circuit.x(notcontrols)
            circuit.mct(control_qubits = allnums, target_qubit = n)
            circuit.x(notcontrols)
        else:
            circuit.mct(control_qubits = allnums, target_qubit = n)
            #circuit.mct(control_qubits = controls, target_qubit = n)

    # Put your code here (spaces for indentation)
    # End Code

    return circuit


def hw4_3_response(circuit):
    # Put your code to find the entangled qubits here
    n = circuit.width()
    a = "0" * n
    one = "1"
    for i in circuit:
        if(i[0].name == "cx"):
            a = a[:i[1][0].index] + one + a[i[1][0].index + 1:]
            a = a[:i[1][1].index] + "x" + a[i[1][1].index + 1:]
    # Put your code here (spaces for indentation)
    # End Code
    return a



def decompose_circuit(circuit):
    dag = qiskit.converters.circuit_to_dag(circuit)
    decomposer = qiskit.transpiler.passes.Unroll3qOrMore()
    decomposer.run(dag)
    decomposed_circuit = qiskit.converters.dag_to_circuit(dag)
    return decomposed_circuit

def hw4_4_response(original_circuit, reg, connection_graph_nx, connection_graph_dictionary):
    decomposed = decompose_circuit(original_circuit)

    c = qiskit.QuantumCircuit(reg)
    new_c = c.compose(decomposed)

    # new_c = qiskit.QuantumCircuit(new_reg)
    # Put your code here
    # End Code

    return new_c
    #return new_c
