# Compute the Number of Times a Pattern Appears in a Text

function count_pattern(text, pattern)
    patternlength = length(pattern)
    count = 0
    for ix in 1:(length(text)-patternlength)
        substring = SubString(text, ix, ix + patternlength - 1)
        if substring == pattern
            count += 1
        end
    end
    count
end


function main()
    text = readline()
    pattern = readline()
    result = count_pattern(text, pattern)
    println(result)
end


main()
