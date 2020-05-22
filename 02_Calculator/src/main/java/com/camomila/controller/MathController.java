package com.camomila.controller;

import com.camomila.exception.UnsupportedMathOperationException;
import com.camomila.math.SimpleMath;
import com.camomila.request.converters.NumberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    @Autowired
    private SimpleMath math;

    @Autowired
    private NumberConverter converter;

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        validateInput(numberOne, numberTwo);
        return math.sum(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        validateInput(numberOne, numberTwo);
        return math.subtract(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        validateInput(numberOne, numberTwo);
        return math.multiply(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divide(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        validateInput(numberOne, numberTwo);
        return math.divide(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        validateInput(numberOne, numberTwo);
        return math.mean(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable("number") String number) throws Exception {
        validateInput(number);
        return math.squareRoot(converter.convertToDouble(number));
    }

    private void validateInput(String number) {
        if (!converter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
    }

    private void validateInput(String numberOne, String numberTwo) {
        if (!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
    }
}
