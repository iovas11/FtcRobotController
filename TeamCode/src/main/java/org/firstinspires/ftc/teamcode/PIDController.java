package org.firstinspires.ftc.teamcode;


public class PIDController {
    private double kp; //proportional gain
    private double ki; //integral gain
    private double kd; // derivative gain

    private double target; //setpoint
    private double integral; // integral term accumulation
    private double previousError; //previous error value

    public PIDController(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.target = 0.0;
        this.integral = 0.0 ;
        this.previousError = 0.0;
    }

    public void setTarget (double target) {
        this.target=target;
        this.integral = 0.0;
        this.previousError= 0.0;
    }
    public double calculateOutput(double currentValue , double deltaTime) {
        double error = target - currentValue;

        //proportional term
        double proportionalTerm= kp*error;

        //integral term
        integral += error * deltaTime;
        double integralTerm = ki * integral;

        //derivative term
        double derivatimeTerm = kd*((error-previousError)/deltaTime);

        //Calculate the output value
        double output = proportionalTerm + integralTerm + derivatimeTerm;

        //update previous error value
        previousError = error;
        return output;
    }
}