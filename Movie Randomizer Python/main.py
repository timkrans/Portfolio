import MethodHandler

z = MethodHandler.Main()
z.printMenu()
g = ""
while g!= "e":
    g = str(input())
    match g:
        case "a":
            z.add()
        case "r":
            z.random()
        case "l":
            z.list()
        case "m":
            z.printMenu()
        case "s":
            z.save()