package nz.ac.auckland.se206.controllers;

public class Subject {
    private String name;
    private double time;
    private double oldTime = 0;
    private double zeroToOne;
    private int percentage;
    private boolean hours = false;

    public Subject() {
        this.name = "";
        this.time = 0;
        this.percentage = 0;
        this.zeroToOne = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public double getTime() {
        return this.time;
    }

    public void setDoubleTime(double time) {
        this.time = time;
    }

    public double getOldTime() {
        return this.oldTime;
    }

    public void setOldTime(double time) {
        this.oldTime = time;
    }

    public void setZeroToOne(double time) {    
        this.zeroToOne = time;
    }

    public void setPercentage(int time) {
        this.percentage = time;
    }

    public double getTotalHours() {
        return this.time/60;
    }

    public boolean setTime(String time) {
        hours = false;
        boolean contained = false;
        if (time.length() == 0) {
            return false;
        }
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == '.') {
                hours = true;
                continue;
            } else if (time.charAt(i) == 'h' || time.charAt(i) == ':') {
                if (time.charAt(i) == 'h') {
                    hours = true;
                    time = time.replace('h','.');
                    contained = true;
                }
                if (time.charAt(i) == ':') {
                    hours = true;
                    time = time.replace(':','.');
                    contained = true;
                }
            } else if (time.charAt(i) < '0' || time.charAt(i) > '9' || time.length() > 5) {
                return false;
            }
        }
        if (contained) {
            Double temp = Double.parseDouble(time);
            Double newTime = 0.0;
            while (temp > 1) {
                newTime++;
                temp -= 1;
            }
            newTime += temp / 0.6;
            newTime = Math.round(newTime * 100.0) / 100.0;
            time = newTime.toString();
        }
        oldTime = this.time;
        if (hours) {
            this.time += Double.parseDouble(time) * 60.0;
        } else {
            this.time += (double) Integer.parseInt(time);
        }
        zeroToOne = this.time / 600.0;
        percentage = (int) (zeroToOne * 100);
        return true;
    }

    public double getZeroToOne() {
        return this.zeroToOne;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public double getHours() {
        return (this.time/60) - (this.oldTime/60);
    }

    public int getExtra() {
        if (this.time > 600) {
            if (this.oldTime - 600 < 0) {
                return ((int) (this.time - 600))/60;
            } else {
                return ((int) (this.time - 600)- (int) (this.oldTime - 600))/60;
            }
        } else {
            return 0;
        }
    }
}
