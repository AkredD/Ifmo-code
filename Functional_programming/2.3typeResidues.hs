

class Circle1 a where
    -- 0elem = 0
    -- (a + o = o + a = a
    o' :: a
    -- (a * e = e * a)
    e' :: a
    -- ассоциативность сложения
    -- a + b = b + a
    -- a + (b + c) = (a + b) + c
    sum' :: a -> a -> a
    -- a + (inv a) = (inv a) + a = o
    inv :: a -> a
    -- ассоциативность умножения
    -- a * (b * c) = (a * b) * c
    mul :: a -> a -> a
    -- дистрибутивность
    -- a * (b + c) = (a * b) + (a * c)
    -- (b + c) * a = (b * a) + (c * a)

data Residues = Err | Neu | Zero | Dat Integer Integer deriving (Show, Ord)

instance Eq Residues where
    (==) Err _ = False
    (==) _ Err = False
    (==) Neu Zero = False
    (==) Zero Neu = False
    (==) Zero (Dat x y) = x == 0
    (==) (Dat x y) Zero = x == 0
    (==) (Dat x y) Neu = x == 1
    (==) Neu (Dat x y) = x == 1
    (==) (Dat x1 y1) (Dat x2 y2) | y1 == y2 = (mod x1 y1) == (mod x2 y2)
                                 | otherwise = False
instance Circle1 Residues where
    o' = Zero
    e' = Neu
    sum' Err _ = Err
    sum' _ Err = Err
    sum' Zero Zero = Zero
    sum' Neu Neu = Err
    sum' Neu Zero = Neu
    sum' Zero Neu = Neu
    sum' Zero a = a
    sum' a Zero = a
    sum' Neu (Dat x y) = Dat (mod (x+1) y) y
    sum' (Dat x y) Neu = Dat (mod (x+1) y) y
    sum' (Dat x1 y1) (Dat x2 y2) | y1 == y2 = Dat (mod (x1+x2) y1) y1
                                 | otherwise = Err
    inv Err = Err    
    inv Zero = Zero
    inv Neu = Neu
    inv (Dat x y) = (Dat (mod (-x) y) y)
    mul Err _ = Err
    mul _ Err = Err
    mul Zero _= Zero
    mul _ Zero = Zero
    mul Neu Neu = Neu
    mul Neu (Dat x y) = (Dat x y)
    mul (Dat x y) Neu = (Dat x y)
    mul (Dat x1 y1) (Dat x2 y2) | y1 == y2 = Dat (mod (x1*x2) y2) y1
                                | otherwise = Err
    

