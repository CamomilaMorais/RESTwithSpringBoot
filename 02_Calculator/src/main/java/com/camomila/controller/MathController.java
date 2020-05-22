package com.camomila.controller;

import com.camomila.exception.UnsupportedMathOperationException;
import com.camomila.math.SimpleMath;
import com.camomila.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return math.subtract(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return math.multiply(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divide(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return math.divide(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable("number") String number) throws Exception {
        if (!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        return math.squareRoot(NumberConverter.convertToDouble(number));
    }
}
