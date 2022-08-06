import qiskit

def hw2_1_response():
    qr1 = qiskit.QuantumRegister(2)
    qc1 = qiskit.QuantumCircuit(qr1)

    qc1.z(qr1[1])
    qc1.swap(qr1[0], qr1[1])
    qc1.x(qr1[1])
    qc1.swap(qr1[0], qr1[1])
    qc1.cx(qr1[0], qr1[1])

    return qc1


import qiskit

def hw2_2_response():
    qr2 = qiskit.QuantumRegister(2)
    qc2 = qiskit.QuantumCircuit(qr2)

    qc2.cnot(qr2[0], qr2[1])
    qc2.h(qr2[1])
    qc2.z(qr2[0])
    qc2.swap(qr2[0], qr2[1])
    qc2.x(qr2[0])

    return qc2


import qiskit

def hw2_3_response():
    qr3 = qiskit.QuantumRegister(3)
    qc3 = qiskit.QuantumCircuit(qr3)
    
    qc3.x(qr3[2])
    qc3.cz(qr3[1], qr3[2])
    qc3.cz(qr3[1], qr3[2])

    return qc3
      