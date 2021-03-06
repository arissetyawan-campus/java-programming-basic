/*  
* This is to demo with boolean, constructor, access modifers, 
* fixed number operators, expression, statements, logic, etc
* arissetyawan.email@gmail.com
*/

package operators;

public class AndroRobot extends ArissanRobot{
   public static void main(String[] argv){
      AndroRobot ar_0 = new AndroRobot();
      ar_0.setPower(false);
      ar_0.setEnergyLevel(100);
      ar_0.setAmoStock(100);
      ar_0.resumeStats();
      ar_0.respondToDanger();
      ar_0.fireEnemy();
      ar_0.fireEnemy();
      
      AndroRobot ar = new AndroRobot();
      ar.setPower(true);
      ar.setEnergyLevel(100);
      ar.setAmoStock(100);
      ar.resumeStats();
      ar.respondToDanger();
      
      ar.printLine();
      ar.setEnemy(true, 0);
      ar.respondToDanger();
      
      ar.setPower(true);
      ar.printLine();
      ar.setEnemy(true, 2);
      ar.respondToDanger();
      
      ar.printLine();
      ar.setEnemy(true, 1);
      ar.reloadAmo();
      ar.respondToDanger();

      ar.printLine();
      ar.setEnemy(true, -1);
      ar.respondToDanger();

      ar.printLine();
      ar.setEnergyLevel(30);
      ar.setEnemy(true, 2);
      ar.respondToDanger();
      ar.fireEnemy();
      ar.fireEnemy();
      ar.fireEnemy();

      ar.printLine();
      ar.setEnergyLevel(67);
      ar.setEnemy(true, 1);
      ar.respondToDanger();
      ar.fireEnemy();
      ar.fireEnemy();
      ar.fireEnemy();

      ar.printLine();
      ar.setEnemy(true, 1);
      ar.setEnergyLevel(31);
      ar.setAmoStock(0);
      ar.respondToDanger();
   }

   AndroRobot(){
     super("Andro robot v1");
   }

   protected void printLine(){
       System.out.println("======================");
   }
}

class ArissanRobot
{
  private boolean power = false;
  private int energyLevel = 0; 
  private final int MIN_WORKING_ENERGY = 30; 
  private boolean charging = false;

  private boolean enemyDetect = false;
  private int amoStock = 0;
  private boolean fireOn = false;
  private boolean dangerLeft = false;
  private boolean dangerRight = false;
  private boolean dangerBack = false;
  private boolean dangerFront = false;

  private void resetPositions(){
    dangerLeft = false;
    dangerRight = false;
    dangerBack = false;
    dangerFront = false;
  }

  protected void setEnemy(boolean enemyDetect, int position)
  {
    if(this.power){
      this.resetPositions();
      this.enemyDetect = enemyDetect;
      if(position==0)
      {
        this.verbose("Enemy detected on left");
        this.dangerLeft = true;
      }

      else if(position==1)
      {
        this.verbose("Enemy detected on right"); 
        this.dangerRight = true;
      }

      else if(position==2)
      {
        this.verbose("Enemy detected on back");
        this.dangerBack = true;
      }

      else
      {      
        this.verbose("Enemy detected on front");
        this.dangerFront = true;
      }
    }

  }

  protected void setPower(boolean powerInput)
  {
    this.power = powerInput;
  }
  
  private void verbose(String status){
    System.out.println("" + status );
  }

  protected void reloadAmo(){
    if(this.power){
      verbose("before reload amo: " + this.amoStock);
      this.amoStock += 1;
      verbose("after reload amo: " + this.amoStock);
    }
  }

  protected void setAmoStock(int amoInput)
  {
    if(this.power){
    this.amoStock = amoInput;
    }
  }

  protected void setEnergyLevel(int energyInput)
  {
    if(this.power){
       this.energyLevel = energyInput;
    }
  }

  protected void fireEnemy()
  {

    if(this.power)
    {
      if(this.energyLevel < this.MIN_WORKING_ENERGY){
        gameOver();
      }
      else
      {
         if(this.energyLevel < this.MIN_WORKING_ENERGY)
         {
            this.verbose("energy level < working energy");
            this.gameOver();
            //this.chargingOn();
         }
         else
         {
            if(this.amoStock > 0)
            {
               int bullets = 3;
               int efforts = 1;
               char charBullet = '\u1028';
               verbose(">> Initializing to fire on");
               verbose("Before: energyLevel: " + this.energyLevel);
               verbose("Before: amoStock: " + this.amoStock);
               this.fireOn = true;
               verbose("Fire !:");
               for(int i=1; i <= this.amoStock; i++)
               {
                  System.out.print(charBullet);
               }
               this.energyLevel -= efforts;
               this.amoStock -= bullets;
               verbose("");
               verbose("after: energyLevel: " + this.energyLevel);
               verbose("after: amoStock: " + this.amoStock);
            }
            else
            {           
             verbose("amo stock <= 0");
             verbose("please reload amo");
            }
         }
       }
    } 
  }

  private void line(){
    System.out.println("---------------");
  }

  private void gameOver(){  
    if(this.power){
      verbose("gameover");
    }
  }

  private void turnPosition()
  {
    if(this.power)
    {
      if(this.energyLevel >= this.MIN_WORKING_ENERGY){
        if(this.dangerLeft){
          verbose("turn left");
        }
        else if(this.dangerRight){
          verbose("turn right");
        }
        else if(this.dangerFront){
          verbose("stay to fire");
        }
        else{
          this.dangerLeft = true;
          this.turnPosition();
          this.turnPosition();
        }
      }
      else
      {
        gameOver();
      }
    }
  }

  protected void respondToDanger(){
    if(!this.power)
    {
       verbose("im off, Sir !");
       gameOver();
    }
    
    else
    {
        if(this.enemyDetect)
        {
          
          this.turnPosition();
          // fire the enemy
          this.fireEnemy();
        }
        else
        {
           verbose("im singing, no enemy! horee !!!!");
        }
    }
  }

  protected void chargingOn(){
    this.verbose("charging...");
    this.charging = true;
    
  }

  protected void resumeStats(){
    line();
    verbose("Resuming stats...");
    line();
    this.verbose("Power: " + power);
    this.verbose("Initializing...");
    this.verbose("charging: " + charging);
    this.verbose("energyLevel: " + energyLevel);
    this.verbose("amoStock: " + amoStock);
    this.verbose("enemyDetect: " + enemyDetect);
    line();
  }

  ArissanRobot(String name)
  {
    this.line();
    this.verbose("Hello...");
    this.verbose("My name is " + name);
    this.verbose("Please turn the power on to continue..");
    this.line();
  }


}

/* Soal,
jawaban dikumpulkan dengan file yg sudah direvisi, kumpulkan ke email:
arissetyawan.email@gmail.com
subject email: NIM - nama
file diattached dalam email
terlambat lebih dari 10 menit, tidak diterima.

>> revisi program diatas agar <<<
1) saya dapat compile dan run menjadi sbb:
>>(point 10);
$ javac uts/NamaAnda_NIM.java
$ java uts.NamaAnda_NIM

2) tiap kali isi amunisi /reload amunisi 'reloadAmo' ada kata2 "amunisi habis, sedang reload!"
>>(point 10);

3) agar batas energy level minimal robot bisa berjalan adalah 10;
>>(point 10);

4) agar tiap kali robot menembak maka amunisanya kurang 4 dari sebelumnya, dan nyawanya kurang 2 dari sebelumnya;
>>(point 20);

5) agar tiap ada musuh yg terdeteksi dibelakang maka dia akan muter kanan, kanan bukan kiri, kiri
>>(point 20);

6) agar kalo ada musuh didepan dia mencetak "apa kabar ? sudah siap tempur?"
>>(point 10);

7) buat satu fungsi /method yg dapat dipanggil dalam robot turunan (yaitu nama anda). method itu berasal dari ArissanRobot. namai method tersebut dengan "sisaMenembak()",
yg mana fungsi itu mencetak "sisa kesempatan menembak = sisa amunisi  / 4". kenapa bagi empat?
karena tiap kali menembak amunisinya telah diset keluar 4.
misal keluarannya: "sisa kesempatan menembak: 4 kali"
>>(point 20);
*/
