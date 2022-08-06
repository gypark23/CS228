
import qiskit

def hw1_0_response():
    qrpre = qiskit.QuantumRegister(3)
    qcpre = qiskit.QuantumCircuit(qrpre)
    # Put your code here (spaces for indentation)
    qcpre.cx(qrpre[0], qrpre[1])
    qcpre.cx(qrpre[2], qrpre[1])
    qcpre.h(qrpre[0])
    qcpre.z(qrpre[2])
    qcpre.swap(qrpre[0], qrpre[2])
    qcpre.x(qrpre[1])
    # End Code

    return qcpre

import qiskit

def hw1_1_response():
    qr1 = qiskit.QuantumRegister(1)
    qc1 = qiskit.QuantumCircuit(qr1)
    
    # Put your code here (spaces for indentation)
    qc1.x(qr1[0])
    qc1.h(qr1[0])
    # End Code

    return qc1


import qiskit

def hw1_2_response():
    qr2 = qiskit.QuantumRegister(2)
    qc2 = qiskit.QuantumCircuit(qr2)

    # Put your code here (spaces for indentation)
    qc2.h(qr2[0])
    qc2.z(qr2[0])
    qc2.x(qr2[0])
    qc2.x(qr2[1])
    # End Code

    return qc2
      




import qiskit

def hw1_3_response():
    qr3 = qiskit.QuantumRegister(2)
    qc3 = qiskit.QuantumCircuit(qr3)
    
    # Put your code here (spaces for indentation)
    qc3.cx(qr3[1], qr3[0])
    qc3.h(qr3[0])
    qc3.swap(qr3[0], qr3[1])
    qc3.x(qr3[0])
    qc3.z(qr3[0])
    qc3.z(qr3[1])
    # End Code

    return qc3
      




import qiskit

def hw1_4_response():
    qr4 = qiskit.QuantumRegister(2)
    qc4 = qiskit.QuantumCircuit(qr4)
    
    # Put your code here (spaces for indentation)
    qc4.h(qr4[0])
    qc4.h(qr4[1])
    qc4.z(qr4[0])
    qc4.z(qr4[1])
    qc4.swap(qr4[0], qr4[1])
    # End Code

    return qc4
