class Circle1 a where
    -- 0elem = 0
    -- (a + o = o + a = a
    o :: a
    -- (a * e = e * a)
    e :: a
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

-- example (Z, +, *)
instance Circle1 Integer where
        o = 0
        e = 1
        sum' a b = a + b
        mul a b = a * b
        inv a = (-) 0 a
        
    