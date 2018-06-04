class Group a where
    -- neutral element
    e :: a
    -- assotiative operation
    op :: a -> a -> a
    -- op (op a b) c == op a (op b c)
    -- invariant element
    inv :: a -> a
    -- e == a + (inv a)

-- example (Z, +)
instance Group Integer where
        e = 0
        op a b = (+) a b
        inv a = (-) 0 a
        