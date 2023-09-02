/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <iostream>
#include <string>
#include <cstddef>

using namespace std;


class Supply{
public:
    //declare variables
    int SleepingBag;
    int CellPhone;
    int Canteen;
    int MedicalSupplies;
public:
    //constructors
    Supply(){
        CellPhone = 0;
        SleepingBag = 0;
        Canteen = 0;
        MedicalSupplies = 0;
    }
};

class Player{
private:
    //declare variables
    string Name;
    int Age;
    double FitLvl;
    int WaterSupl;
    int FoodSupl;
    int YrTraining;
    Supply S1 = Supply();
public:
    //constructors
    Player(string name, int age, double fitLvl, int waterSupl, int foodSupl, int yr){
        Name = name;
        Age = age;
        FitLvl = fitLvl;
        WaterSupl = waterSupl;
        FoodSupl = foodSupl;
        YrTraining = yr;
    }
    //accessor
    int getAge(){
        return Age;
    }
    string getName(){
        return Name;
    }
    double getFit(){
        return FitLvl;
    }
    int getWater(){
        return WaterSupl;
    }
    int getFood(){
        return FoodSupl;
    }
    int getYearT(){
        return YrTraining;
    }
    int getCell(){
        return S1.CellPhone;
    }
    int getSleep(){
        return S1.SleepingBag;
    }
    int getCanteen(){
        return S1.Canteen;
    }
    int getMedical(){
        return S1.MedicalSupplies;
    }
    //mutator
    void setMedical(int x){
        S1.MedicalSupplies = S1.MedicalSupplies + x;
    }
    void setCell(int x){
        S1.CellPhone = S1.CellPhone + x;
    }
    void setCanteen(int x){
        S1.Canteen = S1.Canteen + x;
    }
    void setSleep(int x){
        S1.SleepingBag = S1.SleepingBag + x;
    }
    void setFood(int x){
        FoodSupl = FoodSupl + x;
    }
    void setWater(int x){
        WaterSupl = WaterSupl + x;
    }
    
};

int main()
{
    //declare variables
    int level = 0;
    int sleep, food , water, age, cell, canteen, mendical, year, user;
    double fitLvl;
    string name;
    
    //get informations from user
    cout<< "What is your name"<<endl;
    cin>>name;
    cout<< "What is your fitness level"<<endl;
    cin>>fitLvl;
    cout<< "What is your age"<<endl;
    cin>>age;
    cout<< "What is your amount of water supply"<<endl;
    cin>>water;
    cout<< "What is your amount of food supply"<<endl;
    cin>>food;
    cout<< "What is your amount of years trained"<<endl;
    cin>>year;
    
    //creat a player with the informations
    Player p1 = Player(name, age, fitLvl, water, food, year);
    
    //create a while loop with logic to play the game
    while(level != 5 && p1.getWater() >= 0 && p1.getFood() >= 0){
        if(level == 0){
            p1.setCell(1);
            p1.setCanteen(1);
            p1.setMedical(1);
            p1.setSleep(1);
            cout<<"Welcome to the game. \n In order to complete this game you will have to trade your supplies to allow you to get water and food supplies"<< endl;
            cout<<"We have given you 1 cellphone, 1 canteen, 1 sleeping bag, and 1 medical supply"<< endl;
            cout<<"Each time you trade an item you will given the option of the trade for how much water they would give you or how much food they would give you"<<endl;
            cout<<"During the end of the game fitLvl will determine how much food and water it taken per level, but for the first 2 levels it will be just a cost of 1 each"<<endl;
            cout<<"Years of training can allow you to get to a fast start and skip levels"<<endl;
            level++;
        }
        else if(level == 1){
            cout<<"Level 1: A stranger walks up and offers 8 food supply for the phone or 2 water supply"<<endl;
            cout<<"Type 0 for food and 1 for water anything else will refuse the deal"<<endl;
            cin>>user;
            if(user == 0){
                p1.setCell(-1);
                p1.setFood(8);
            }
            else if(user == 1){
                p1.setCell(-1);
                p1.setWater(2);
            }
            p1.setWater(-1);
            p1.setFood(-1);
            level++;
        }
        else if(level == 2){
            cout<<"Level 2: A stranger walks up and offers 2 food supply for the sleeping bag or 1 water supply"<<endl;
            cout<<"Type 0 for food and 1 for water anything else will refuse the deal"<<endl;
            cin>>user;
            if(user == 0){
                p1.setSleep(-1);
                p1.setFood(2);
            }
            else if(user == 1){
                p1.setSleep(-1);
                p1.setWater(1);
            }
            cout<<"If you have more then one year of traing you will skip the next level and go to the final level"<<endl;
            if(p1.getYearT()>=1){
                level++;
            }
            p1.setWater(-1);
            p1.setFood(-1);
            level++;
        }
        else if(level == 3){
            cout<<"Level 3: A stranger walks up and offers 3 food supply for the Canteen or 1 water supply"<<endl;
            cout<<"Type 0 for food and 1 for water anything else will refuse the deal"<<endl;
            cin>>user;
            if(user == 0){
                p1.setCanteen(-1);
                p1.setFood(3);
            }
            else if(user == 1){
                p1.setCanteen(-1);
                p1.setWater(1);
            }
            cout<<"From this point forward if fitness level is less then 2 then you will lose two water and food each level"<<endl;
            if(p1.getFit()>= 2){
                p1.setWater(-1);
                p1.setFood(-1);
            }  
            else{
                p1.setWater(-2);
                p1.setFood(-2);  
            }
            level++;
        }
        else if(level == 4){
            cout<<"Level 4: A stranger walks up and offers 10 food supply for the Medical Supplies or 4 water supply"<<endl;
            cout<<"Type 0 for food and 1 for water anything else will refuse the deal"<<endl;
            cout<<"Again from this point forward if fitness level is less then 2 then you will lose two water and food each level"<<endl;
            cin>>user;
            if(user == 0){
                p1.setMedical(-1);
                p1.setFood(10);
            }
            else if(user == 1){
                p1.setMedical(-1);
                p1.setWater(4);
            }
            if(p1.getFit()>= 2){
                p1.setWater(-1);
                p1.setFood(-1);
            }  
            else{
                p1.setWater(-2);
                p1.setFood(-2);  
            }
            level++;
        }
    }
    if(level == 5){
        printf("You made it to the top of the grand canyon!!");
    }
    else{
        printf("You did not reach the top.");
    }
    return 0;
}