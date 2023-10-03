@echo off
setlocal enabledelayedexpansion

REM 设置目标文件夹的路径
set "targetFolder=F:\Project\BlogSpringBoot"

REM 设置要忽略的文件夹的名称，用空格分隔
set "ignoreFolders=.idea node_modules .next server"

call :DeleteMatchingFiles "%targetFolder%"
goto :eof

:DeleteMatchingFiles
REM 输出当前处理的文件夹的完整路径
echo Processing folder: %1

REM 遍历子文件夹并检查是否要忽略
for /d %%D in ("%1\*") do (
    set "folderName=%%~nxD"
    REM 检查是否要忽略该文件夹
    echo !ignoreFolders! | findstr /i "\<!folderName!\>" >nul
    if errorlevel 1 (
        call :DeleteMatchingFiles "%%D"
    ) else (
        echo Ignored folder "%%D"
    )
)

REM 遍历文件并删除符合条件的文件
for %%F in ("%1\*.js" "%1\*.js.map") do (
    set "fileName=%%~nF"
    if "%fileName:~-3%"==".js" (
        set "fileName=!fileName:.js=!"
    )
    set "tsFile=%1\!fileName!.ts"

    REM 检查是否存在同名的ts文件
    if exist "!tsFile!" (
        del "%%F"
        echo Deleted file "%%F" in folder %1
    ) else (
        echo Skipped file "%%F" (No matching !fileName!.ts file found in folder %1)
    )
)
goto :eof
