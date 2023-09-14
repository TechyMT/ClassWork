from symbols import defaultSymbol
f = open("/home/pict/31376/assignment3/macro_pass/testcase1.txt", "r")
mnt = open("/home/pict/31376/assignment3/macro_pass/MNT.txt", "w")
kpt = open("/home/pict/31376/assignment3/macro_pass/KPTAB.txt", "w")
mdt_ptr = 1
mnt_ptr = 1
macroFlag = 0
macrodefFlag = 0
pntabFile = ""
kp_ptr = 0
pp_ptr = 0
kp_index = 0
for line in f:
    wordArr = line.split(" ")
    if macroFlag == 0:
        print("macro", wordArr)
        if wordArr[0] == "MACRO\n":
            macroFlag = 1
            pntabFile = open(
                "/home/pict/31376/assignment3/macro_pass/PNTAB"+str(mnt_ptr)+".dat", "w")
            macrodefFlag = 0
    elif macroFlag ==1 and wordArr[0] == "MEND\n":
        macroFlag = 0
        mnt_ptr+= 1
    else:
        print("line")
        pntab_ptr = 0
        if not defaultSymbol(wordArr[0]):
            print(wordArr)
            mnt.write(wordArr[0])

            for word in wordArr[1:]:
                if not macrodefFlag:
                    if "=" in word:
                        kp_ptr += 1
                        pntab_ptr += 1
                        kp = word.split("=")
                        kpt.write(str(kp_ptr)+" "+kp[0].split("&")
                                [1]+" "+kp[1]+"\n" if len(kp) > 1 else " "+"\n")
                        pntabFile.write(str(pntab_ptr) + " "+word.split("&")[1].split("=")[0]+"\n")
                        
                    else:
                        pp_ptr += 1
                        pntab_ptr += 1
                        pp = word.split("&")
                        pntabFile.write(str(pntab_ptr) + " "+word.split("&")[1]+"\n")
            macrodefFlag = 1
            mnt.write(" "+str(pp_ptr) +" "+str(kp_ptr)+" "+str(kp_index+1)+"\n")
            kp_index+= kp_ptr





           
