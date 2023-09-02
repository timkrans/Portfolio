import pickle, Movie, random
class Main():
    num = []

    def __init__(self):
        with open("movie.pickle", "rb") as infile:
            reconst = pickle.load(infile)
        infile.close
        self.num = reconst

    def printMenu(self):
        print("a = add movie")
        print("r = random movie")
        print("s = save movies")
        print("l = list of movies")
        print("m = list menu")
        print("e = exit")

    def save(self):
        with open("movie.pickle","wb") as outfile:
            pickle.dump(self.num,outfile)
        outfile.close
        
    def list(self):
        x=0
        if len(self.num)==0:
            print("No movies added")
        else:
            while x < len(self.num):
                print(self.num[x].toString())
                x+=1
    
    def add(self):
        name = str(input("Enter movie name:"))
        where =  str(input("Enter where to see movie:"))
        rate = int(input("Enter " + name + " rating:"))
        mov = Movie.Movie(name,where,rate)
        self.num.append(mov)
    
    def random(self):
        if len(self.num) == 0:
            print("No movies added")
        else:
            g = random.randint(0,len(self.num)-1)
            print(self.num[g].toString())
            h = input("Do you wish to remove "+ self.num[g].title + "from the list(y/n)")
            if h == "y":
                self.num.remove(self.num[g])