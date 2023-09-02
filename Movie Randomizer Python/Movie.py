import pickle

class Movie:
    title = ""
    where = ""
    rating = 0
    def __init__(self,title,where,rate):
        self.title = title
        self.where = where
        self.rating = rate

    def toString(self):
       string = "Movie: "+ self.title +"\nWhere to find it: " + self.where + "\nRating: " + str(self.rating)
       return string
