package com.company.model;

import com.company.data.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadFile2 {
    public List<Vehicle> catalog;




    public ReadFile2(String s) {
        catalog = new ArrayList<Vehicle>();


        try {
            read(catalog, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ReadFile2() {
        catalog = new ArrayList<Vehicle>();
    }



    @Override
    public String toString() {
        String s="";
        for ( Vehicle b: catalog){
            s += b+"\n";
        }
        return s;
    }


    public int getLength(){
        return catalog.size();
    }

//    public  Veicle findElement(int id){
//
//    }

    public void sortXYZ(){

    }



    public static void main(String[] args) throws IOException {
        //Readfile r = new Readfile();
          ReadFile2 r = new ReadFile2(
                "src/com.company.files/pricelist.txt"
        );

        System.out.println("\nIteration through the list with Efor");
        System.out.println(r);


//        r.sortYearName();
//        System.out.println("\nonthefly  Sorted  and modelCatalog");
//        System.out.println(r);
//
//
//        System.out.println("Test search");
//        Veicle aa = r.findElement(501);
//        System.out.println(aa);
//        aa = r.findElement(202);
//        System.out.println(aa);
//        aa = r.findElement(301);
//        System.out.println(aa);

    }


    private void read(List <Vehicle> cat, String csvFilePath) throws Exception {

        String line = null;
        BufferedReader bufferedReader = null;

        try {
            String path = new File(csvFilePath).getAbsolutePath();
            File csvFile = new File(path);
            FileReader fileReader = new FileReader(csvFile);
            //  FileReader fileReader = new FileReader(csvFile, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);

            //System.out.println("sono qui!");
            while ((line = bufferedReader.readLine()) != null) {
                if(line.charAt(0) == '#'){
                   continue;
                }

                String[] csvLineElements = line.split(",");

//                for (int i = 0; i < csvLineElements.length; i++) {
//                    System.out.print(csvLineElements[i] + " , ");
//                }
//                System.out.println("");
                Vehicle nb;
                DateTimeFormatter dateTimeFormatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                if(csvLineElements[0].charAt(0)   == '#') {
//                    //skip
//                }


                int id = (int) Integer.parseInt(csvLineElements[0].trim());
                String type = csvLineElements[1].trim();
                String maker = csvLineElements[2].trim();
                int year = (int) Integer.parseInt(csvLineElements[3].trim());
                String model = csvLineElements[4].trim();
                double cost = (double)Double.parseDouble(csvLineElements[5].trim());
                double weight = (double) Double.parseDouble(csvLineElements[6].trim());


                if (id>=400 && id <=499 ) {
                    //LocalDate ld =LocalDate.parse(csvLineElements[1], dateTimeFormatter);
                    //445,B,Yamaha,2022,YZF-R7,8999,187
                    Motorbike b = new Motorbike(id,maker,year, model,cost,weight);

                    cat.add(b);

                }
                //584,C,Ford,2020,Limited 4dr SuperCrew 5.5 ft. SB (3.5L 6cyl Turbo 10A),67735,2327,
                //    rear wheel drive,gas
                if (id>=500 && id <=599 ) {
                    String drivetype = csvLineElements[7].trim();
                    String fueltype = csvLineElements[8].trim();

                    Car b = new Car(id,maker,year, model,cost,weight,drivetype,fueltype);
                    cat.add(b);
                }
                //201,C,Toyota,2024,XLE 4dr Hatchback (2.0L 4cyl gas/electric hybrid CVT),31395,1582,
                // front wheel drive,hybrid,15
                if(id >=200 && id <=299){
                    String drivetype = csvLineElements[7].trim();
                    String fueltype = csvLineElements[8].trim();
                    double bat = (double)Double.parseDouble(csvLineElements[9].trim());

                    ECar b = new ECar(id,maker,year, model,cost,weight,drivetype,fueltype,bat);
                    cat.add(b);

                }
                if(id >=100 && id <=199){
                    String drivetype = csvLineElements[7].trim();
                    String fueltype = csvLineElements[8].trim();
                    double bat = (double)Double.parseDouble(csvLineElements[9].trim());

                    ECar b = new ECar(id,maker,year, model,cost,weight,drivetype,fueltype,bat);
                    cat.add(b);

                }
            }
        }
        catch (IOException e) {
            System.out.println("Error Occured while parsing csv file." + e);
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println("Error Occured while parsing csv file numberformat");
            e.printStackTrace();
        }
        finally {
            bufferedReader.close();
        }

    }





}


