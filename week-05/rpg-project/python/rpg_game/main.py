from tkinter import *
from random import randint

dimension = 72
width_in_squares = 5
height_in_squares = 5

class Game_object(object):
    def __init__(self, position_x, position_y, costume_image):
        self.position_x = position_x
        self.position_y = position_y
        self.costume_image = costume_image

    def draw(self, canvas):
        self.photo = PhotoImage(file="img/" + self.costume_image + ".png")
        canvas.create_image(self.position_x*dimension, self.position_y*dimension, anchor = NW, image = self.photo)
            
    def is_character(self):
        return isinstance(self, Character)

class Character(Game_object):
    def __init__(self, position_x, position_y, costume_image):
        super().__init__(position_x, position_y, costume_image)

    def move_up(self):
        pass

    def move_down(self):
        pass

    def move_left(self):
        pass

    def move_right(self):
        pass

    def is_floor(self, x, y):
        return game.map_[y][x] == 0

class Hero(Character):
    def __init__(self, position_x=0, position_y=0, costume_image="hero-down"):
        super().__init__(position_x, position_y, costume_image)

    def move_up(self):
        self.costume_image = "hero-up"
        if self.position_y > 0:
            if self.is_floor(self.position_x, self.position_y-1):
                self.position_y -= 1

    def move_down(self):
        self.costume_image = "hero-down"
        if self.position_y < len(game.map_)-1:
            if self.is_floor(self.position_x, self.position_y+1):
                self.position_y += 1

    def move_left(self):
        self.costume_image = "hero-left"
        if self.position_x > 0:
            if self.is_floor(self.position_x-1, self.position_y):
                self.position_x -= 1

    def move_right(self):
        self.costume_image = "hero-right"
        if self.position_x < len(game.map_[0])-1:
            if self.is_floor(self.position_x+1, self.position_y):
                self.position_x += 1 

class Boss(Character):
    def __init__(self, position_x, position_y, costume_image="boss"):
        super().__init__(position_x, position_y, costume_image)

class Skeleton(Character):
    def __init__(self, position_x, position_y, costume_image="skeleton"):
        super().__init__(position_x, position_y, costume_image)

class Game():
    def __init__(self, map_=[[0,1,1,0,1],[0,1,0,0,0],[0,1,1,1,0],[0,0,0,0,0],[1,1,0,1,1]], objects=[]):
        self.map_ = map_
        self.objects = objects

    def init(self):
        self.render_tiles()
        self.render_hero()
        self.render_boss()
        self.render_skeletons()
       
    def render_tiles(self):
        for x in range(len(self.map_)):
           for y in range(len(self.map_[x])):
              if self.map_[y][x] == 0:
                  game.objects.append(Game_object(x, y, "floor"))
              else:
                  game.objects.append(Game_object(x, y, "wall"))
   
    def render_hero(self):
        self.objects.append(Hero())        

    def render_boss(self):
        while True:
              x = randint(0,4)
              y = randint(0,4)
              if self.map_[y][x] == 0 and (x != 0 and y != 0):
                  self.objects.append(Boss(x,y))
                  break

    def render_skeletons(self, number_of_skeletons=3):
        for num in range(number_of_skeletons):
            while True:
              x = randint(0,4)
              y = randint(0,4)
              if self.map_[y][x] == 0 and (x != 0 and y != 0):
                  self.objects.append(Skeleton(x,y))
                  break  

root = Tk()
root.wm_title("RPG Game")

canvas = Canvas(root, width=dimension*width_in_squares, height=dimension*height_in_squares)

game = Game()
game.init()

def on_key_press(e):
    if e.keycode == 9:
        sys.exit() 
    elif e.keycode == 111:
        for game_object in game.objects:
            if game_object.is_character():
                game_object.move_up()
    elif e.keycode == 116:
        for game_object in game.objects:
            if game_object.is_character():
                game_object.move_down()
    elif e.keycode == 113:
        for game_object in game.objects:
            if game_object.is_character():
                game_object.move_left()
    elif e.keycode == 114:
        for game_object in game.objects:
            if game_object.is_character():
                game_object.move_right() 

    for game_object in game.objects:
        game_object.draw(canvas)

canvas.bind("<KeyPress>", on_key_press)
canvas.pack()
canvas.focus_set()
for game_object in game.objects:
    game_object.draw(canvas)
root.mainloop()
