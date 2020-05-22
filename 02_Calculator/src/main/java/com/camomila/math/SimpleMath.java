package com.camomila.math;

import com.camomila.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class SimpleMath {

    public Double sum(Double firstNumber, Double secondNumber) {
        return firstNumber + secondNumber;
    }

    public Double subtract(Double firstNumber, Double secondNumber) {
        return firstNumber - secondNumber;
    }

    public Double multiply(Double firstNumber, Double secondNumber) {
        return firstNumber * secondNumber;
    }

    public Double divide(Double firstNumber, Double secondNumber) {
        return firstNumber / secondNumber;
    }

    public Double mean(Double firstNumber, Double secondNumber) {
        return (firstNumber + secondNumber)/2;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}
